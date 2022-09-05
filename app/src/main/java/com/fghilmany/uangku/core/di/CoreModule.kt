package com.fghilmany.uangku.core.di

import androidx.room.Room
import com.fghilmany.uangku.BuildConfig
import com.fghilmany.uangku.core.data.DataRepository
import com.fghilmany.uangku.core.data.local.LocalDatasource
import com.fghilmany.uangku.core.data.local.room.Database
import com.fghilmany.uangku.core.data.remote.RemoteDatasource
import com.fghilmany.uangku.core.data.remote.network.ApiServices
import com.fghilmany.uangku.core.utils.DATABASE_NAME
import com.fghilmany.uangku.core.utils.PreferenceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<Database>().dao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()


    }

    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl("https://fghilmany.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(ApiServices::class.java)
    }

}

val repositoryModule = module {
    single { RemoteDatasource(get(), get()) }
    single { LocalDatasource(get()) }
    single { PreferenceProvider(get()) }
    single { DataRepository(get(), get(), get()) }
}