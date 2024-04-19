package com.cst.cstacademyunibuc.data

import com.cst.cstacademyunibuc.ApplicationController
import com.cst.cstacademyunibuc.data.tasks.GetAllProductsTask
import com.cst.cstacademyunibuc.data.tasks.InsertProductTask
import com.cst.cstacademyunibuc.helpers.extensions.logErrorMessage
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel

object ProductsRepository{

    fun insertProduct(model: ProductAPIModel, onSuccess: () -> Unit) {
        InsertProductTask(onSuccess).execute()
    }

    fun getAllProducts() {
        GetAllProductsTask { products ->
            "listSuccess: ${products.map { it.name }}".logErrorMessage()
        }.execute()
    }

    fun insertAllProducts(list: List<ProductAPIModel>) {

    }
}