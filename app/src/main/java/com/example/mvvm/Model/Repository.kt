package com.example.mvvm.Model

import com.example.masterslaveview.dp.Product
import com.example.masterslaveview.dp.ProductDao
import com.example.mvvm.Model.Retrofit.RetroInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ProductRepository(private val productDao: ProductDao, private val retrofit: RetroInterface) {

    fun fetchProducts(): Flow<List<Product>> = flow {
        val count = productDao.getCount()
        if (count > 0) {
            productDao.getAll().collect { products ->
                emit(products)
            }
        } else {
            retrofit.getProducts().collect { response ->
                if (response.isSuccessful) {
                    response.body()?.products?.let { products ->
                        productDao.insertAll(products)
                        emit(products)
                    } ?: emit(emptyList())
                } else {
                    throw Exception("Failed to fetch data from API")
                }
            }
        }
    }

    fun getSavedProducts(): Flow<List<Product>> {
        return productDao.getSavedProducts()
    }

    suspend fun saveProduct(product: Product) {
        product.saved = true
        productDao.saveProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        product.saved = false
        productDao.saveProduct(product)
    }
}