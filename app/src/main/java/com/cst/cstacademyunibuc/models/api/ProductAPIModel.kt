package com.cst.cstacademyunibuc.models.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cst.cstacademyunibuc.helpers.Utils.Constants.ApiArguments.ARG_API_CATEGORY
import com.cst.cstacademyunibuc.helpers.Utils.Constants.ApiArguments.ARG_API_TITLE
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