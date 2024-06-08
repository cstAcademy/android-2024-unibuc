package com.cst.cstacademyunibuc.data.repositories.products

import androidx.lifecycle.LiveData
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

interface ProductsRepository {
    suspend fun insertProduct(model: ProductAPIModel)
    suspend fun insertAllProducts(model: List<ProductAPIModel>)
    fun getAllProducts(): LiveData<List<ProductAPIModel>>
}