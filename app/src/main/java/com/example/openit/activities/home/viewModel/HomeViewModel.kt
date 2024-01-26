package com.example.openit.activities.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openit.data.remote.client.RetrofitClient
import com.example.openit.utils.TAG
import com.example.openit.utils.getXYaxis
import com.example.openit.activities.home.model.LinkData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val dataApi = MutableLiveData<LinkData>();
    private val isCallComplete = MutableLiveData<Boolean>(false)
    init{
       getApiValue()
    }

    fun getLinkData() : LiveData<LinkData>{
        return dataApi
    }

    fun getIsCallComplete() : LiveData<Boolean>{
        return isCallComplete
    }
    fun getApiValue(){
        val call: Call<LinkData> = RetrofitClient.apiService.getLinkData()
        call.enqueue(object : Callback<LinkData> {
            override fun onResponse(call: Call<LinkData>, response: Response<LinkData>) {
                if (response.isSuccessful) {
                    val linkData: LinkData? = response.body()
                    if (linkData != null) {
                        dataApi.postValue(getXYaxis(linkData))
                    }
                    isCallComplete.postValue(true)
                } else {
                    Log.d(TAG, "onResponse: not successfully" )
                    isCallComplete.postValue(true)
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
                isCallComplete.postValue(true)
            }
        })
    }

}