package com.example.matchmateassignment.data.repository

import com.example.matchmateassignment.data.remote.UserApi
import javax.inject.Inject

class MainUserRepository @Inject constructor(
    private val api: UserApi
) {
    suspend fun getUserList(page: Int = 1) = api.getUserList(page)
}