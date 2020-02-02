package com.nechvolod.konstantin.kernelapp.ui.activity.splash

import androidx.lifecycle.MutableLiveData
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.base.cache.BaseCache
import com.nechvolod.konstantin.kernelapp.data.entities.AuthCredentials
import timber.log.Timber

class SplashVM(private val authCredCache: BaseCache<AuthCredentials>): BaseViewModel() {

    val isAuthenticated = MutableLiveData<Boolean>(false)
    init {
        if(authCredCache.isCached) {
            isAuthenticated.value = authCredCache.get().token.isNotEmpty()
            Timber.tag("AUTHCACHE").d(authCredCache.get().toString())
        }
    }

}