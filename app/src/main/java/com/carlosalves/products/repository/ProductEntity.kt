package com.carlosalves.products.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carlosalves.products.api.dto.ProductData
import kotlin.String

@Entity(tableName = "Product")
data class ProductEntity (@PrimaryKey val id: Int,
                          val title: String,
                          val description: String,
                          val price: Double,
                          val discountPercentage: Double,
                          val stock: Int,
                          val rating: Double,
                          val imageURL: String)

fun ProductData.mapToEntity() = ProductEntity(
    this.id,
    this.title,
    this.description,
    this.price,
    this.discountPercentage,
    this.stock,
    this.rating,
    this.thumbnail)