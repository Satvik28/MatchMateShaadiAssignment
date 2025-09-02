package com.example.matchmateassignment.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserProfileDbDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserProfileDbData>)

    @Query("SELECT * FROM user_profile_db")
    fun getAllUsersWithPaging(): PagingSource<Int, UserProfileDbData>

    @Query("DELETE FROM user_profile_db")
    suspend fun clearAll()

    @Query("UPDATE user_profile_db SET status = :status WHERE uuid = :uuid")
    suspend fun updateStatus(uuid: String, status: UserStatus)
}