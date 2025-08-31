package com.example.matchmateassignment.utils

import com.example.matchmateassignment.data.local.UserProfileDbData
import kotlin.math.pow
import kotlin.math.sqrt

object MatchScore {

    data class MyUser(
        val age: Int = 45,
        val city: String = "Pune",
        val diet: String = "Non-Veg",
        val familyType: String = "Nuclear"
    )

    val user = MyUser()

    fun getMatchScore(otherUser: UserProfileDbData): Int {
        val ageDifference = (user.age - otherUser.age).toDouble()
        val cityMatchScore = if (user.city.equals(otherUser.city, ignoreCase = true)) 1.0 else 0.0
        val dietMatchScore = if (user.diet.equals(otherUser.dietOption, ignoreCase = true)) 1.0 else 0.0
        val familyMatchScore = if (user.familyType.equals(otherUser.familyType, ignoreCase = true)) 1.0 else 0.0

        val euclideanDistance = sqrt(
            ageDifference.pow(2.0) + cityMatchScore.pow(2.0)
                    + dietMatchScore.pow(2.0) + familyMatchScore.pow(2.0)
        )

        val maxEuclideanDistance = sqrt(AppConstants.MAX_AGE_DIFFERENCE.pow(2.0) + 3.0)
        val normalizedValue = 100 * (1 - (euclideanDistance / maxEuclideanDistance))
        return normalizedValue.coerceIn(0.0, 100.0).toInt()
    }

}