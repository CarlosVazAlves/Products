package com.carlosalves.products.api.dto

import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("width")
    var width: Double,

    @SerializedName("height")
    var height: Double,

    @SerializedName("depth")
    var depth: Double,
)
