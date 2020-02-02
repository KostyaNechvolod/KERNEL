package com.nechvolod.konstantin.kernelapp.ui.fragment.sign_in

import com.nechvolod.konstantin.kernelapp.R
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.base.arch.SingleLiveEvent
import com.nechvolod.konstantin.kernelapp.base.models.NavigationModel

class SignInVM : BaseViewModel() {

    val loginButtonClickedEvent = SingleLiveEvent<Boolean>()

    fun onLoginButtonClicked(){
        loginButtonClickedEvent.value = true
        navControllerLiveEvent.value = NavigationModel(actionId = R.id.action_signInFragment_to_main_navigation)
    }

    fun openSignUpWebViewFragment(){
//        navControllerLiveEvent.value = NavigationModel(R.id.action_signUpFragment_to_signUpWebViewFragment)
    }
}