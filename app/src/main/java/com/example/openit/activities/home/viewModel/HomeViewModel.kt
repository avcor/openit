package com.example.openit.activities.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openit.activities.home.repository.HomeRepository
import com.example.openit.constants.DashBoardVMtag
import com.example.openit.data.remote.type.ApiResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repository: HomeRepository ): ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(DashBoardVMtag, " exception ${throwable} ${coroutineContext}")
    }
    init{
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                repository.getData()
        }
    }
    fun getResponse(): LiveData<ApiResponseType>{
        return repository.getResponse()
    }

}