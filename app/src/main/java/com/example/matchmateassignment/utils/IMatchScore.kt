package com.example.matchmateassignment.utils

import com.example.matchmateassignment.data.local.UserProfileDbData

interface IMatchScore {
    fun getScore(otherUser: UserProfileDbData): Int
}