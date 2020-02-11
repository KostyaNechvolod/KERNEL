package com.nechvolod.konstantin.kernelapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nechvolod.konstantin.kernelapp.base.BaseViewModel
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository
import com.nechvolod.konstantin.kernelapp.utils.LoadingState
import kotlinx.coroutines.launch

class HomeVM(private val userRepository: TtnRepository) : BaseViewModel() {
    val ttnList = MutableLiveData<List<TtnModel>>()

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = userRepository.data

    init {
//        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}