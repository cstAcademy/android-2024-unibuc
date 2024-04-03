package com.cst.cstacademyunibuc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cst.cstacademyunibuc.models.ProductModel

@Dao
public interface ProductsDao {

    @Insert
    fun insertProduct(model: ProductModel)

    @Delete
    fun deleteProduct(model: ProductModel)

    @Query("SELECT * FROM product_model WHERE id = :id")
    fun getProductById(id: String)

    @Query("SELECT * FROM product_model")
    fun getAllProducts()
}