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

@Entity("product_model")
data class ProductModel(
    @PrimaryKey
    override val id: String,
    override val title: String,
    val description: String
) : CartItemModel (
    id = id,
    title = title,
    type = CartItemType.PRODUCT
)

@Entity("category_model")
data class CategoryModel(
    @PrimaryKey(autoGenerate = true)
    override val id: String = "",
    override val title: String,
    val description: String
) : CartItemModel (
    id = id,
    title = title,
    type = CartItemType.CATEGORY
)