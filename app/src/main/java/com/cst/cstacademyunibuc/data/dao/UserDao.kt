package com.cst.cstacademyunibuc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cst.cstacademyunibuc.data.models.user.UsersWithRole
import com.cst.cstacademyunibuc.models.ProductModel
import com.cst.cstacademyunibuc.models.api.ProductAPIModel
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.RoleType
import com.cst.cstacademyunibuc.models.user.UserModel

@Dao
public interface UserDao {

    @Insert
    fun insertUser(model: UserModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertRole(model: RoleModel)

    @Query("SELECT * FROM RoleModel WHERE type = :roleType")
    fun getUsersWithRole(roleType: RoleType): UsersWithRole
}