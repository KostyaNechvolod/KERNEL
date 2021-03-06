package com.nechvolod.konstantin.kernelapp.ui.fragment.sign_in

import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.base.arch.SingleLiveEvent

class SignInVM : BaseViewModel() {

    val loginButtonClickedEvent = SingleLiveEvent<Boolean>()

    fun onLoginButtonClicked(){
        loginButtonClickedEvent.value = true
    }

    fun openSignUpWebViewFragment(){
//        navControllerLiveEvent.value = NavigationModel(R.id.action_signUpFragment_to_signUpWebViewFragment)
    }
}