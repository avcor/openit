package com.example.openit

import com.example.openit.retrofitInterface.HomeApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.inopenapp.com/"

    val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
    val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $TOKEN")
                    .build()
                return chain.proceed(newRequest)
            }
        })
    }

    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.inopenapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val apiService = retrofit.create(HomeApi::class.java)
}