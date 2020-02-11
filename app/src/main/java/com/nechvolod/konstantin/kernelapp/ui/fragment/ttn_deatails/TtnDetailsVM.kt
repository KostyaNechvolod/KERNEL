package com.nechvolod.konstantin.kernelapp.ui.fragment.ttn_deatails

import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository

class TtnDetailsVM(private val userRepository: TtnRepository) : BaseViewModel() {

    //    var data = MutableLiveData<TtnModel>()
//    var data : LiveData<TtnModel>? = null
    val data = userRepository.data

    fun getData(id: Int) {
//        viewModelScope.launch {
//            try {
//                data.value = userRepository.getTtnById(id).value
//            } catch (e: Exception) {
//                Timber.tag("ROOMA").d(e)
//            }
//        }
    }

}