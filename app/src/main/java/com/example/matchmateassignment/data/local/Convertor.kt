package com.example.matchmateassignment.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun convertFromStatus(status: UserStatus): String = status.name

    @TypeConverter
    fun convertToStatus(value: String): UserStatus = UserStatus.valueOf(value)
}
