package com.example.matchmateassignment.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDbDao {

    @Upsert()
    suspend fun upsertUsers(users: List<UserProfileDbData>)

    @Query("SELECT * FROM user_profile_db")
    fun getAllUsers(): Flow<List<UserProfileDbData>>

    @Query("UPDATE user_profile_db SET status = :status WHERE uuid = :uuid")
    suspend fun updateStatus(uuid: String, status: UserStatus)

}