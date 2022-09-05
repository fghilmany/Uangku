package com.fghilmany.uangku.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fghilmany.uangku.core.data.DataRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashMainViewModel(private val dataRepository: DataRepository): ViewModel() {

    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        GlobalScope.launch {
            delay(500)
            mutableLiveData.postValue(SplashState.StartSplash)
            delay(2800)
            mutableLiveData.postValue(SplashState.FinishSplash)
        }
    }

}

sealed class SplashState {
    object FinishSplash : SplashState()
    object StartSplash : SplashState()
}
