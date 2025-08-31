package com.example.matchmateassignment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_db")
data class UserProfileDbData(
    @PrimaryKey val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val city: String,
    val state: String,
    val country: String,
    val dob: String,
    val age: Int,
    val pictureUrl: String,
    val familyType: String,
    val dietOption: String,
    val status: UserStatus = UserStatus.DEFAULT
) {
    fun getFullName(): String {
        return "$title $firstName $lastName"
    }

    fun getCityState(): String {
        return "$city, $state"
    }
}
