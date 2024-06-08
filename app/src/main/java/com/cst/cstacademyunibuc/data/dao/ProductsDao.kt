package com.cst.cstacademyunibuc.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

@Dao
public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(model: ProductAPIModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllProducts(model: List<ProductAPIModel>)

    @Query("SELECT * FROM product_model")
    fun getAllProducts(): LiveData<List<ProductAPIModel>>

    @Delete
    fun deleteProduct(model: ProductAPIModel)

    @Query("SELECT * FROM product_model WHERE id = :id")
    fun getProductById(id: String): ProductAPIModel
}