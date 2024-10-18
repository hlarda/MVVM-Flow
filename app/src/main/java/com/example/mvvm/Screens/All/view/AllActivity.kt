package com.example.mvvm.Screens.All.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.masterslaveview.dp.AppDatabase
import com.example.mvvm.Model.ProductRepository
import com.example.mvvm.Model.Retrofit.NetworkServiceImpl
import com.example.mvvm.Screens.All.viewModel.AllProductViewModel
import com.example.mvvm.Screens.All.viewModel.UiState
import com.example.mvvm.Screens.All.viewModel.ViewModelFactory
import com.example.mvvm.databinding.ActivityAllBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AllActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllBinding
    private val viewModel: AllProductViewModel by viewModels {
        ViewModelFactory(
            ProductRepository(
                AppDatabase.getDatabase(this).productDao(),
                NetworkServiceImpl.retrofitService
            )
        )
    }
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.list.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        adapter.submitList(uiState.products)
                        binding.progressBar.visibility = View.GONE
                        binding.list.visibility = View.VISIBLE
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@AllActivity, uiState.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.getAllProducts()
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter(
            onProductClick = { product -> /* Handle product click */ },
            onProductSave = { product ->
                viewModel.saveProduct(product)
                Toast.makeText(this, product.title + " saved", Toast.LENGTH_SHORT).show()
            }
        )
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
    }
}