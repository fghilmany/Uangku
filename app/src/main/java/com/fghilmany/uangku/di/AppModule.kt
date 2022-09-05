package com.fghilmany.uangku.di

import com.fghilmany.uangku.ui.splash.SplashMainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashMainViewModel(get()) }
}