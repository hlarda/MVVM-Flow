package com.example.mvvm.Model.Retrofit

import com.example.masterslaveview.dp.Product
import com.example.masterslaveview.dp.Root
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface RetroInterface {
    @GET("products")
    fun  getProducts(): Flow<Response<Root>>
}