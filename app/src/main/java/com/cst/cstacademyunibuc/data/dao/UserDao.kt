package com.cst.cstacademyunibuc.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.cst.cstacademyunibuc.data.models.user.UsersWithRole
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.RoleType
import com.cst.cstacademyunibuc.models.user.UserModel

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(model: UserModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRole(model: RoleModel)

    @Transaction
    @Query("SELECT * FROM RoleModel WHERE type = :roleType")
    fun getUsersWithRole(roleType: RoleType): LiveData<UsersWithRole>
}