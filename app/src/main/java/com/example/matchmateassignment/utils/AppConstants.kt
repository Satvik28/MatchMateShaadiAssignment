package com.example.matchmateassignment.utils

object AppConstants {
    const val REMOTE_API_RESULTS = 10
    const val DATA_LOAD_THRESHOLD = 3
    const val REMOTE_API_SEED = "matchmate"
    const val REMOTE_API_INCLUDED_FIELDS = "gender,name,location,login,dob,picture"
    const val REMOTE_API_BASE_URL = "https://randomuser.me/"
    val familyList: List<String> = listOf("Joint", "Nuclear")
    val dietOptions: List<String> = listOf("Veg", "Jain", "Non-Veg", "Vegan")

    const val MAX_AGE_DIFFERENCE = 30.0
}