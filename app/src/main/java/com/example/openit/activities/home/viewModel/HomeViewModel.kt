package com.example.openit.activities.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openit.activities.home.repository.HomeRepository
import com.example.openit.data.remote.type.ApiResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(var repository: HomeRepository ): ViewModel() {

    init{
        viewModelScope.launch {
            repository.getData()
        }
    }
    fun getResponse(): LiveData<ApiResponseType>{
        return repository.getResponse()
    }

}