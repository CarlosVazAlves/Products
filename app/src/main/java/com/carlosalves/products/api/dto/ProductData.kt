package com.carlosalves.products.api.dto

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("price")
    var price: Double,

    @SerializedName("discountPercentage")
    var discountPercentage: Double,

    @SerializedName("rating")
    var rating: Double,

    @SerializedName("stock")
    var stock: Int,

    @SerializedName("tags")
    var tags: List<String>,

    @SerializedName("brand")
    var brand: String,

    @SerializedName("sku")
    var sku: String,

    @SerializedName("weight")
    var weight: Int,

    @SerializedName("dimensions")
    var dimensions: Dimensions,

    @SerializedName("warrantyInformation")
    var warrantyInformation: String,

    @SerializedName("shippingInformation")
    var shippingInformation: String,

    @SerializedName("availabilityStatus")
    var availabilityStatus: String,

    @SerializedName("reviews")
    var reviews: List<Review>,

    @SerializedName("returnPolicy")
    var returnPolicy: String,

    @SerializedName("minimumOrderQuantity")
    var minimumOrderQuantity: Int,

    @SerializedName("meta")
    var meta: Meta,

    @SerializedName("images")
    var images: List<String>,

    @SerializedName("thumbnail")
    var thumbnail: String,
)
