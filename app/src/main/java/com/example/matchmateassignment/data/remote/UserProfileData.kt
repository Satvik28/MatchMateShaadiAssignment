package com.example.matchmateassignment.data.remote


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

    override fun toString(): String {
        return "UserProfileData(gender='$gender', name=$name, location=$location, login=$login, dob=$dob, picture=$picture)"
    }
}
