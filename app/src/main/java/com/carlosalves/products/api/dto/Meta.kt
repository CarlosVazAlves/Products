package com.carlosalves.products.api.dto

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("createdAt")
    var createdAt: String,

    @SerializedName("updatedAt")
    var updatedAt: String,

    @SerializedName("barcode")
    var barcode: String,

    @SerializedName("qrCode")
    var qrCode: String,
)
