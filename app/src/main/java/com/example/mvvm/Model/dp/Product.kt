package com.example.masterslaveview.dp

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Root(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: Long,

    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String?,
    val sku: String,
    val weight: Long,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val returnPolicy: String,
    val minimumOrderQuantity: Long,
    val thumbnail: String,
    var saved: Boolean = false
)