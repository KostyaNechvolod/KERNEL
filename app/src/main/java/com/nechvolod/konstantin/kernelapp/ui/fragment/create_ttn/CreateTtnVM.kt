package com.nechvolod.konstantin.kernelapp.ui.fragment.create_ttn

import androidx.lifecycle.MutableLiveData
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository

class CreateTtnVM (private val userRepository: TtnRepository) : BaseViewModel() {

    val fields: MutableMap<String, MutableLiveData<String>?> = mutableMapOf(
        "ttn_number" to MutableLiveData(),
        "ttn_date" to MutableLiveData(),
        "truck_number" to MutableLiveData(),
        "trailer_number" to MutableLiveData(),
        "driver" to MutableLiveData()
    )
}