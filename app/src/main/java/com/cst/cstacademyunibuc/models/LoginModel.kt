package com.cst.cstacademyunibuc.models

import com.cst.cstacademyunibuc.R

data class LoginModel(
    val username: String,
    val password: String
)

enum class WeekDaysType(val key: String, val colorResourceId: Int) {
    UNKNOWN("unknown", android.R.color.transparent),
    MONDAY("monday", R.color.black),
    TUESDAY("tuesday", R.color.white),
    WEDNESDAY("wednesday", R.color.yellow);

    companion object {
        fun fromKey(key: String) = entries.find { type ->
            type.key == key
        } ?: UNKNOWN
    }
}

sealed class CalendarDayModel(
    val type: WeekDaysType
)

class DayMonday() : CalendarDayModel(
    type = WeekDaysType.MONDAY
)

fun String.getWeekDay() = WeekDaysType.fromKey(this)