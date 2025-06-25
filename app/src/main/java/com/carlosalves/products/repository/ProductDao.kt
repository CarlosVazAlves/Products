package com.carlosalves.products.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT (SELECT COUNT(*) FROM Product) == 0")
    fun tableIsEmpty(): Boolean

    @Query("SELECT * FROM Product")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM Product where id = :productId")
    fun getProductById(productId: Int): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductEntity>)

    @Query("DELETE FROM Product WHERE id = :productId")
    fun deleteProductById(productId: Int)

    @Query("DELETE FROM Product")
    fun deleteAllProducts()
}