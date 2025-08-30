package com.example.matchmateassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [UserProfileDbData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserProfileDataBase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDbDao
}