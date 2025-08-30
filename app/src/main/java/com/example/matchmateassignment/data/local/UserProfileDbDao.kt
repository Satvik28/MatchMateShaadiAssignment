package com.example.matchmateassignment.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserProfileDbDao {
    @Upsert()
    suspend fun upsertUsers(users: List<UserProfileDbData>)

    @Query("SELECT * FROM user_profile_db")
    fun getAllUsersWithPaging(): PagingSource<Int, UserProfileDbData>

    @Query("DELETE FROM user_profile_db")
    suspend fun clearAll()

    @Query("UPDATE user_profile_db SET status = :status WHERE uuid = :uuid")
    suspend fun updateStatus(uuid: String, status: UserStatus)
}