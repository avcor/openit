package com.example.openit.retrofitInterface

import com.example.openit.home.LinkData
import retrofit2.Call
import retrofit2.http.GET


interface HomeApi {
    @GET("api/v1/dashboardNew")
    fun getLinkData(): Call<LinkData>
}