package com.example.databindingdummyprojects.api

import com.example.databindingdummyprojects.models.ProductApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ProductApi {


    @GET("products")
    suspend fun getAllProducts(): Response<ProductApiResponse>


}
