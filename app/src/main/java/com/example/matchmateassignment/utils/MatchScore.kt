package com.example.matchmateassignment.utils

import com.example.matchmateassignment.data.local.MyUser
import com.example.matchmateassignment.data.local.UserProfileDbData
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.max

class MatchScore @Inject constructor(private val user: MyUser) : IMatchScore {

    override fun getScore(otherUser: UserProfileDbData): Int {

        if (user.age == 0 || otherUser.age == 0) return MIN_SCORE

        val ageDifference = abs(user.age - otherUser.age)

        val ageScore =
            if (ageDifference > AppConstants.MAX_AGE_DIFFERENCE) 1.0 else ageDifference.toDouble() / max(otherUser.age, user.age)

        val cityMatchScore = if (user.city.equals(otherUser.city, ignoreCase = true)) 0.0 else 1.0
        val dietMatchScore =
            if (user.diet.equals(otherUser.dietOption, ignoreCase = true)) 0.0 else 1.0
        val familyMatchScore =
            if (user.familyType.equals(otherUser.familyType, ignoreCase = true)) 0.0 else 1.0

        // likely values are closer to zero
        val score =
            100 * (cityMatchScore * CITY_WEIGHT + dietMatchScore * DIET_WEIGHT + familyMatchScore * FAMILY_WEIGHT + ageScore * AGE_WEIGHT) / WEIGHT_SUM

        return MAX_SCORE - score.toInt()
    }

    companion object {
        const val AGE_WEIGHT = 100
        const val CITY_WEIGHT = 20
        const val DIET_WEIGHT = 50
        const val FAMILY_WEIGHT = 20
        const val MAX_SCORE = 100
        const val MIN_SCORE = 0
        const val WEIGHT_SUM = AGE_WEIGHT + CITY_WEIGHT + DIET_WEIGHT + FAMILY_WEIGHT

    }

}