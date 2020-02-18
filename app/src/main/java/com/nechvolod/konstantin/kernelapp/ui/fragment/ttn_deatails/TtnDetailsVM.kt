package com.nechvolod.konstantin.kernelapp.ui.fragment.ttn_deatails

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class TtnDetailsVM(private val userRepository: TtnRepository) : BaseViewModel() {

    var data : LiveData<TtnModel>? = null

    fun getData(id: Int) {
        viewModelScope.launch {
            try {
                data = userRepository.getTtnById(id)
                Timber.tag("ROOMA").d(data?.value?.driverName)
            } catch (e: Exception) {
                Timber.tag("ROOMA").d(e)
            }
        }
    }

}