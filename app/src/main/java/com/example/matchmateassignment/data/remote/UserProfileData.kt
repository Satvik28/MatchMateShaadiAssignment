package com.example.matchmateassignment.data.remote

import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.utils.AppConstants

data class UserProfileData(
    val gender: String,
    val name: Name,
    val location: Location,
    val login: Login,
    val dob: Dob,
    val picture: Picture
) {
    data class Name(val title: String, val first: String, val last: String)
    data class Location(val city: String, val state: String, val country: String)
    data class Login(val uuid: String)
    data class Dob(val date: String, val age: Int)
    data class Picture(val large: String)

    companion object {
        fun UserProfileData.toDbData(): UserProfileDbData {
            return UserProfileDbData(
                uuid = login.uuid,
                title = name.title,
                firstName = name.first,
                lastName = name.last,
                gender = gender,
                city = location.city,
                state = location.state,
                country = location.country,
                dob = dob.date,
                age = dob.age,
                pictureUrl = picture.large,
                familyType = AppConstants.familyList.random(),
                dietOption = AppConstants.dietOptions.random(),
                status = UserStatus.DEFAULT
            )
        }

        fun List<UserProfileData>.toDbList(): List<UserProfileDbData> {
            return this.map { it.toDbData() }
        }
    }
}
