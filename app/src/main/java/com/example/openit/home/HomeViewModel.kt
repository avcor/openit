package com.example.openit.home

import android.annotation.TargetApi
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openit.RetrofitClient
import com.example.openit.TAG
import com.example.openit.getXYaxis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class HomeViewModel: ViewModel() {

    private val dataApi = MutableLiveData<LinkData>();

    init{
       getApiValue()
    }

    fun getLinkData() : LiveData<LinkData>{
        return dataApi
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
                } else {
                    Log.d(TAG, "onResponse: not successfully" )
                }
            }

            override fun onFailure(call: Call<LinkData>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }
        })
    }

}