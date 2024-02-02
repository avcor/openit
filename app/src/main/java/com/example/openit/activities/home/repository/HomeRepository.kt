package com.example.openit.activities.home.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.openit.activities.home.model.LinkData
import com.example.openit.data.remote.type.ApiResponseType
import com.example.openit.data.remote.client.RetrofitClient
import com.example.openit.utils.mapGraphData
import javax.inject.Inject

class HomeRepository @Inject constructor(){

    private val responseType = MutableLiveData<ApiResponseType>()
    fun getResponse(): LiveData<ApiResponseType>{
        return responseType
    }
    suspend fun getData(){
        val result = RetrofitClient.apiService.getLinkResponse()
        var response: ApiResponseType

        if(result.isSuccessful){
            if(result.body() != null){
                response = ApiResponseType.Success(result.body() as LinkData, mapGraphData(result.body()!!));
            }else{
                response = ApiResponseType.NoData()
            }
        }else{
            response = ApiResponseType.Failure()
        }
        responseType.postValue(response);
    }
}