package com.example.mvvm.Screens.Saved.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.masterslaveview.dp.AppDatabase
import com.example.mvvm.Model.ProductRepository
import com.example.mvvm.Model.Retrofit.NetworkServiceImpl
import com.example.mvvm.Screens.Saved.viewModel.SavedViewModel
import com.example.mvvm.Screens.Saved.viewModel.SavedViewModelFactory
import com.example.mvvm.databinding.ActivitySavedBinding
import kotlinx.coroutines.launch

class SavedActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedBinding
    private val viewModel: SavedViewModel by viewModels {
        SavedViewModelFactory(
            ProductRepository(
                AppDatabase.getDatabase(this).productDao(),
                NetworkServiceImpl.retrofitService
            )
        )
    }
    private lateinit var adapter: SavedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launch {
            viewModel.savedProducts.collect { products ->
                adapter.submitList(products)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = SavedAdapter(
            onProductClick = { product -> /* Handle product click */ },
            onProductDelete = { product -> viewModel.deleteProduct(product) }
        )
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
    }
}