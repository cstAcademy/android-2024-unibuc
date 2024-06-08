package com.cst.cstacademyunibuc.models.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ARG_ID)
    val id: Int = 0,
    val username: String,
    val password: String,
    @ColumnInfo(name = ARG_ROLE)
    val role: RoleType
) {
    companion object {
        const val ARG_ID = "id"
        const val ARG_ROLE = "role"
    }
}