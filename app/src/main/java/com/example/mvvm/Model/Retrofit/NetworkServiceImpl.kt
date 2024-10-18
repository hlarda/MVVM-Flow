package com.example.mvvm.Model.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceImpl {
    private const val BASE_URL = "https://dummyjson.com/"

    val retrofitService: RetroInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetroInterface::class.java)
    }
}
