package com.carlosalves.products.repository

import com.carlosalves.products.api.dto.ProductData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.map

@Singleton
class ProductsRepository @Inject constructor(private val database: ProductDao) {
    fun checkIfDbHasData() = database.tableIsEmpty()

    fun getAllProducts() = database.getAllProducts()

    fun getProductById(productId: Int) = database.getProductById(productId)

    fun storeAllProducts(products: List<ProductData>) = database.insertProducts(products.map { it.mapToEntity() })

    fun storeProduct(product: ProductData) = database.insertProduct(product.mapToEntity())

    fun deleteProduct(product: ProductData) = database.deleteProductById(product.id)

    fun deleteAllProducts() = database.deleteAllProducts()
}