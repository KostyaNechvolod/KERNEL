package com.nechvolod.konstantin.kernelapp.base.models

import androidx.annotation.StringRes

data class ToastModel constructor(var message: String? = null, @StringRes val idResMessage: Int? = null)