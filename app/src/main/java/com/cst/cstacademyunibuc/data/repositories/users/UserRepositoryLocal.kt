package com.cst.cstacademyunibuc.data.repositories.users

import com.cst.cstacademyunibuc.data.dao.UserDao
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.RoleType
import com.cst.cstacademyunibuc.models.user.UserModel

class UserRepositoryLocal(private val dao: UserDao): UserRepository {
    override suspend fun insertUserWithRole(user: UserModel) {
        val role = RoleModel(user.role)

        dao.insertUser(user)
        dao.insertRole(role)
    }

    override fun getPlebUsers() = dao.getUsersWithRole(RoleType.USER_PLEB)
    override fun getAdminUsers() = dao.getUsersWithRole(RoleType.ADMIN)
}