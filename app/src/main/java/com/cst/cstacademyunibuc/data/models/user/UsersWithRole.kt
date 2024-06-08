package com.cst.cstacademyunibuc.data.models.user

import androidx.room.Embedded
import androidx.room.Relation
import com.cst.cstacademyunibuc.models.user.RoleModel
import com.cst.cstacademyunibuc.models.user.RoleModel.Companion.ARG_TYPE
import com.cst.cstacademyunibuc.models.user.UserModel
import com.cst.cstacademyunibuc.models.user.UserModel.Companion.ARG_ID
import com.cst.cstacademyunibuc.models.user.UserModel.Companion.ARG_ROLE

class UsersWithRole(
    @Embedded val role: RoleModel,
    @Relation(
        parentColumn = ARG_TYPE,
        entityColumn = ARG_ROLE
    ) val users: List<UserModel>
)