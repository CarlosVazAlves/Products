package com.carlosalves.products.api.dto

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("rating")
    var rating: Int,

    @SerializedName("comment")
    var comment: String,

    @SerializedName("date")
    var date: String,

    @SerializedName("reviewerName")
    var reviewerName: String,

    @SerializedName("reviewerEmail")
    var reviewerEmail: String,
)
