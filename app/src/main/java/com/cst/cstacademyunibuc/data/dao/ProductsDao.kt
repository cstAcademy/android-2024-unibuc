package com.cst.cstacademyunibuc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

@Dao
public interface ProductsDao {

    @Insert
    fun insertProduct(model: ProductAPIModel)

    @Delete
    fun deleteProduct(model: ProductAPIModel)

    @Query("SELECT * FROM product_model WHERE id = :id")
    fun getProductById(id: String): ProductAPIModel

    @Query("SELECT * FROM product_model")
    fun getAllProducts(): List<ProductAPIModel>
}