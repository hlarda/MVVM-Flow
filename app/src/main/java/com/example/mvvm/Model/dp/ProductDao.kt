package com.example.masterslaveview.dp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount(): Int
    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<Product>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<Product>)
    @Query("SELECT * FROM products WHERE saved = 1")
    fun getSavedProducts(): Flow<List<Product>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: Product)
}