package com.nechvolod.konstantin.kernelapp.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.nechvolod.konstantin.kernelapp.base.arch.SingleLiveEvent
import com.nechvolod.konstantin.kernelapp.base.models.NavigationModel
import com.nechvolod.konstantin.kernelapp.base.models.ToastModel

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val navControllerLiveEvent = SingleLiveEvent<NavigationModel>()
    val toastLiveEvent = SingleLiveEvent<ToastModel>()
    val hideKeyboard = SingleLiveEvent<Unit>()
    val updateDataBinding = SingleLiveEvent<Unit>()

    fun addObserver(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    fun removeObserver(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    override fun onCleared() {
//        disposables.dispose()
        super.onCleared()
    }
}