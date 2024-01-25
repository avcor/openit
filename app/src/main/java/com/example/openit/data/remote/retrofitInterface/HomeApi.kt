package com.example.openit.data.remote.retrofitInterface

import com.example.openit.activities.home.model.LinkData
import retrofit2.Call
import retrofit2.http.GET


interface HomeApi {
    @GET("api/v1/dashboardNew")
    fun getLinkData(): Call<LinkData>
}