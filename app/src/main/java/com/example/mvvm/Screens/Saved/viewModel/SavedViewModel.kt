package com.example.mvvm.Screens.Saved.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterslaveview.dp.Product
import com.example.mvvm.Model.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SavedViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _savedProducts = MutableStateFlow<List<Product>>(emptyList())
    val savedProducts: StateFlow<List<Product>> get() = _savedProducts

    init {
        getSavedProducts()
    }

    private fun getSavedProducts() {
        viewModelScope.launch {
            repository.getSavedProducts().collect { products ->
                _savedProducts.value = products
            }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
            getSavedProducts()
        }
    }
}