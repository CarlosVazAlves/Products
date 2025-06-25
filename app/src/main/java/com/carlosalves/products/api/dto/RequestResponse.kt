package com.carlosalves.products.api.dto

import com.google.gson.annotations.SerializedName

data class RequestResponse(
    @SerializedName("products")
    var products: List<ProductData>,

    @SerializedName("total")
    var total: Int,

    @SerializedName("skip")
    var skip: Int,

    @SerializedName("limit")
    var limit: Int,
)