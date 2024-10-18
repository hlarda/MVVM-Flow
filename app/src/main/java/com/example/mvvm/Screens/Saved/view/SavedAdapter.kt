package com.example.mvvm.Screens.Saved.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.masterslaveview.dp.Product
import com.example.mvvm.databinding.CardSavedListBinding

class SavedAdapter(
    private val onProductClick: (Product) -> Unit,
    private val onProductDelete: (Product) -> Unit
) : ListAdapter<Product, SavedAdapter.ProductViewHolder>(ProductDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardSavedListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: CardSavedListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.titlefra.text = product.title
            Glide.with(binding.root)
                .load(product.thumbnail)
                .into(binding.thumbnail)
            binding.root.setOnClickListener { onProductClick(product) }
            binding.deleteBtn.setOnClickListener { onProductDelete(product) }
        }
    }

    companion object ProductDiff : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}