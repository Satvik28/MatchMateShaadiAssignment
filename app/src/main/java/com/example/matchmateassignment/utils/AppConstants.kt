package com.example.matchmateassignment.utils

object AppConstants {
    const val REMOTE_API_RESULTS = 15
    const val DATA_LOAD_THRESHOLD = 5
    const val REMOTE_API_SEED = "matchmate"
    const val REMOTE_API_INCLUDED_FIELDS = "gender,name,location,login,dob,picture"
    const val REMOTE_API_NATIONALITY = "in,us"
    const val REMOTE_API_BASE_URL = "https://randomuser.me/"
    val familyList: List<String> = listOf("Joint", "Nuclear")
    val dietOptions: List<String> = listOf("Veg", "Jain", "Non-Veg", "Vegan")
}