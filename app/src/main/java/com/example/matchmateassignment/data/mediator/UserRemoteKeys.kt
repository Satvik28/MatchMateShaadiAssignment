package com.example.matchmateassignment.data.mediator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class UserRemoteKeys(
    @PrimaryKey val uuid: String,
    val prevPage: Int?,
    val nextPage: Int?
)