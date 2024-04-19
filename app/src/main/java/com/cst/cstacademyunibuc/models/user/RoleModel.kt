package com.cst.cstacademyunibuc.models.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cst.cstacademyunibuc.models.WeekDaysType

enum class RoleType(val key: Int) {
    UNKNOWN(-1),
    ADMIN(0),
    USER_PLEB(1);

    companion object {
        fun fromKey(key: Int) = RoleType.entries.find { type ->
            type.key == key
        } ?: UNKNOWN
    }
}

@Entity
class RoleModel(
    @PrimaryKey
    @ColumnInfo(name = ARG_TYPE)
    val type: RoleType
) {
    companion object {
        const val ARG_TYPE = "type"
    }
}