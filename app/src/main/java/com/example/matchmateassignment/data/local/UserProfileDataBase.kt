package com.example.matchmateassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.matchmateassignment.data.mediator.RemoteKeyDao
import com.example.matchmateassignment.data.mediator.UserRemoteKeys


@Database(
    entities = [UserProfileDbData::class, UserRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class UserProfileDataBase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDbDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}