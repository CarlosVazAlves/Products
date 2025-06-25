package com.carlosalves.products.api

import com.carlosalves.products.api.dto.RequestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("products/")
    suspend fun searchProducts(
        @Query("limit") engine: Int = 0,
    ): Response<RequestResponse>
}