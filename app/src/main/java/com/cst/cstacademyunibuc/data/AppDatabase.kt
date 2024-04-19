package com.cst.cstacademyunibuc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cst.cstacademyunibuc.data.dao.ProductsDao
import com.cst.cstacademyunibuc.data.dao.UserDao
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.UserModel

@Database(
    entities = [
        ProductAPIModel::class,
        UserModel::class,
        RoleModel::class
    ],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
    abstract val userDao: UserDao
}