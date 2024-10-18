package com.example.mvvm.Screens.All.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterslaveview.dp.Product
import com.example.mvvm.Model.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.fetchProducts().collect { productList ->
                    _uiState.value = UiState.Success(productList)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch products: ${e.message}")
                Log.e("AllProductViewModel", "Failed to fetch products: ${e.message}")
            }
        }
    }

    fun saveProduct(product: Product) {
        viewModelScope.launch {
            try {
                repository.saveProduct(product)
                Log.d("AllProductViewModel", "Product saved successfully")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to save product: ${e.message}")
                Log.e("AllProductViewModel", "Failed to save product: ${e.message}")
            }
        }
    }
}