package com.cst.cstacademyunibuc.data.repositories.users

import androidx.lifecycle.LiveData
import com.cst.cstacademyunibuc.data.models.user.UsersWithRole
import com.cst.cstacademyunibuc.models.user.UserModel

interface UserRepository {
    suspend fun insertUserWithRole(user: UserModel)

    fun getPlebUsers(): LiveData<UsersWithRole>
    fun getAdminUsers(): LiveData<UsersWithRole>
}