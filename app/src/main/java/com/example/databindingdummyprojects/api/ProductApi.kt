package com.example.databindingdummyprojects.api

import com.example.databindingdummyprojects.models.ProductApiResponse
import com.example.databindingdummyprojects.models.ProductApiResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductApi {


    @GET("products")
    suspend fun getAllProducts(): Response<ProductApiResponse>

    @GET("products/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String
    ): Response<ProductApiResponseItem>


    //https://fakestoreapi.com/products/1
}
