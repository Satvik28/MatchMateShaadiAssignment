package com.example.matchmateassignment.data

import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.data.remote.UserProfileData
import com.example.matchmateassignment.utils.AppConstants

fun UserProfileData.toDbData(): UserProfileDbData {
    val fullName = "${name.first} ${name.last}"
    return UserProfileDbData(
        uuid = login.uuid,
        fullName = fullName,
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
