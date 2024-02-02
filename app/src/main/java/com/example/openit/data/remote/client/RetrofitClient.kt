package com.example.openit.data.remote.client

import com.example.openit.constants.BEARER_TOKEN_KEY
import com.example.openit.constants.TOKEN
import com.example.openit.data.remote.retrofitInterface.HomeApi
import com.example.openit.global.AuthorizationData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.inopenapp.com/"

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", AuthorizationData.bear_token)
                    .build()
                return chain.proceed(newRequest)
            }
        })
    }

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val apiService = retrofit.create(HomeApi::class.java)

}