package com.cst.cstacademyunibuc.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CartItemType(val key: Int) {
    PRODUCT(0),
    CATEGORY(1)
}

sealed class CartItemModel(
    open val id: String,
    open val title: String,
    open val type: CartItemType
)

data class ProductModel(
    override val id: String,
    override val title: String,
    val description: String
) : CartItemModel (
    id = id,
    title = title,
    type = CartItemType.PRODUCT
)

data class CategoryModel(
    override val id: String = "",
    override val title: String,
    val description: String
) : CartItemModel (
    id = id,
    title = title,
    type = CartItemType.CATEGORY
)