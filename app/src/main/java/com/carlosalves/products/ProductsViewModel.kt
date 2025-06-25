package com.carlosalves.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosalves.products.api.ApiService
import com.carlosalves.products.api.dto.ProductData
import com.carlosalves.products.repository.ProductItem
import com.carlosalves.products.repository.ProductsRepository
import com.carlosalves.products.repository.mapToItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val apiCalls: ApiService, private val repository: ProductsRepository) : ViewModel() {

    private var _dbIsEmpty = MutableStateFlow(true)
    val dbIsEmpty = _dbIsEmpty.asStateFlow()
    private val productsList = mutableListOf<ProductItem>()

    fun checkForProductsInDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _dbIsEmpty.value = repository.checkIfDbHasData()
        }
    }

    fun fetchProducts() {
        if (!dbIsEmpty.value) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiCalls.searchProducts()
                if (response.isSuccessful) {
                    storeProducts(response.body()!!.products)
                }
            } catch (exception: Exception) {

            }
        }
    }

    fun loadProducts() {
        if (productsList.isNotEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            productsList.addAll(repository.getAllProducts().map { it.mapToItem() })
        }
    }

    fun getProductsList() = productsList.toList()

    fun getProduct(productId: Int) = productsList.first { it.id == productId }

    private fun storeProducts(products: List<ProductData>) {
        repository.storeAllProducts(products)
        _dbIsEmpty.value = false
    }
}