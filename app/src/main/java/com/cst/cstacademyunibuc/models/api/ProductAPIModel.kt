package com.cst.cstacademyunibuc.models.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cst.cstacademyunibuc.helpers.Utils.Constants.ApiArguments.ARG_API_CATEGORY
import com.cst.cstacademyunibuc.helpers.Utils.Constants.ApiArguments.ARG_API_TITLE
import com.cst.cstacademyunibuc.models.CartItemModel
import com.cst.cstacademyunibuc.models.CategoryModel
import com.cst.cstacademyunibuc.models.ProductModel
import com.google.gson.annotations.SerializedName

@Entity("product_model")
class ProductAPIModel(
    @PrimaryKey
    val id: String,
    @SerializedName(ARG_API_TITLE)
    val name: String,
    val description: String,
    @SerializedName(ARG_API_CATEGORY)
    val categoryName: String
)

fun List<ProductAPIModel>.toCartItems(): List<CartItemModel> {
    val items = mutableListOf<CartItemModel>()

    this.groupBy { it.categoryName }
        .forEach {
            val categoryModel = CategoryModel(
                id = it.key,
                title = it.key,
                description = it.key
            )

            val products = it.value.map { apiModel ->
                ProductModel(
                    id = apiModel.id,
                    title = apiModel.name,
                    description = apiModel.description
                )
            }

            items.add(categoryModel)
            items.addAll(products)
        }

    return items
}