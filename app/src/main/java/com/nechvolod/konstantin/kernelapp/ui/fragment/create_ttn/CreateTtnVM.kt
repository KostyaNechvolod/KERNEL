package com.nechvolod.konstantin.kernelapp.ui.fragment.create_ttn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository
import com.nechvolod.konstantin.kernelapp.utils.LoadingState
import kotlinx.coroutines.launch

class CreateTtnVM (private val userRepository: TtnRepository) : BaseViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val fields: MutableMap<String, MutableLiveData<String>> = mutableMapOf(
        "ttn_number" to MutableLiveData(""),
        "ttn_date" to MutableLiveData(""),
        "truck_number" to MutableLiveData(""),
        "trailer_number" to MutableLiveData(""),
        "driver" to MutableLiveData(""),
        "sender" to MutableLiveData("")
    )
    val codeList = MutableLiveData<List<TtnModel.Code>>()

    fun additemToDb() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.addItem(TtnModel(
                    ttnNumber = (fields["ttn_number"]?.value!!),
                    ttnDate = (fields["ttn_date"]?.value!!),
                    trailerPlate = (fields["truck_number"]?.value!!),
                    trackPlate = (fields["trailer_number"]?.value!!),
                    driverName = (fields["driver"]?.value!!),
                    senderName = (fields["sender"]?.value!!),
                    codeList = codeList.value!!
                    ))
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}