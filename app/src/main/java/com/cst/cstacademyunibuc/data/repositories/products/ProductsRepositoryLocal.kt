package com.cst.cstacademyunibuc.data.repositories.products

import androidx.lifecycle.LiveData
import com.cst.cstacademyunibuc.data.dao.ProductsDao
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

class ProductsRepositoryLocal(private val dao: ProductsDao): ProductsRepository {

    override suspend fun insertProduct(model: ProductAPIModel) {
       dao.insertProduct(model)
    }

    override suspend fun insertAllProducts(model: List<ProductAPIModel>) {
        dao.insertAllProducts(model)
    }

    override fun getAllProducts(): LiveData<List<ProductAPIModel>> = dao.getAllProducts()
}