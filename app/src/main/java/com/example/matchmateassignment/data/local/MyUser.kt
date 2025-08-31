package com.example.matchmateassignment.data.local

data class MyUser(
    val age: Int = 45,
    val city: String = "Pune",
    val diet: String = "Non-Veg",
    val familyType: String = "Nuclear"
) {
    companion object {
        val INSTANCE by lazy { MyUser() }
    }
}
