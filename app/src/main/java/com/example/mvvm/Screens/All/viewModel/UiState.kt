package com.example.mvvm.Screens.All.viewModel

import com.example.masterslaveview.dp.Product

sealed class UiState {
    object Loading : UiState()
    data class Success(val products: List<Product>) : UiState()
    data class Error(val message: String) : UiState()
}