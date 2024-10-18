package com.example.mvvm.Screens.All.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.masterslaveview.dp.Product
import com.example.mvvm.databinding.CardItemListBinding

class ProductAdapter(
    private val onProductClick: (Product) -> Unit,
    private val onProductSave: (Product) -> Unit
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        Log.d("ProductAdapter", "Binding product: $product")
        holder.apply {
            binding.titlefra.text = product.title
            Glide.with(binding.root)
                .load(product.thumbnail)
                .into(binding.thumbnail)
            binding.root.setOnClickListener { onProductClick(product) }
            binding.saveBtn.setOnClickListener {
                Log.d("ProductAdapter", "Save button clicked")
                onProductSave(product)
            }
        }
    }

    class ProductViewHolder(val binding: CardItemListBinding) : RecyclerView.ViewHolder(binding.root)
}

class ProductDiff : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}