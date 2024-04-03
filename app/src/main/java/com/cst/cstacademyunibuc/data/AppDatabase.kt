package com.cst.cstacademyunibuc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cst.cstacademyunibuc.data.dao.ProductsDao
import com.cst.cstacademyunibuc.models.CategoryModel
import com.cst.cstacademyunibuc.models.ProductModel

@Database(
    entities = [ ProductModel::class, CategoryModel::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val productsDao: ProductsDao
}