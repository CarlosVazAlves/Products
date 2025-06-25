package com.carlosalves.products.repository

data class ProductItem (val id: Int,
                        val title: String,
                        val description: String,
                        val price: Double,
                        val discountPercentage: Double,
                        val stock: Int,
                        val rating: Double,
                        val imageURL: String)

fun ProductEntity.mapToItem() = ProductItem(
    this.id,
    this.title,
    this.description,
    this.price,
    this.discountPercentage,
    this.stock,
    this.rating,
    this.imageURL)
