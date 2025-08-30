package com.example.matchmateassignment.data.repository

import android.util.Log
import com.example.matchmateassignment.data.local.UserProfileDataBase
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.data.remote.UserApi
import com.example.matchmateassignment.data.toDbList
import com.example.matchmateassignment.ui.UiEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainUserRepository @Inject constructor(
    private val api: UserApi,
    localDatabase: UserProfileDataBase
) {
    val dao = localDatabase.userProfileDao()

    fun getUsers(): Flow<UiEvents<List<UserProfileDbData>>> = flow {
        emit(UiEvents.Loading)
        emitAll(
            dao.getAllUsers().onEach { localData ->
                if (localData.isEmpty()) {
                    try {
                        val response = api.getUserList()
                        val dbData = response.results.toDbList()
                        dao.upsertUsers(dbData)
                    } catch (e: Exception) {
                        Log.e(TAG,"Exception occurred ${e.message}")
                        emit(UiEvents.Error("Failed to get data from remote"))
                    }
                }
            }.map { localData -> UiEvents.Success(localData) }
        )
    }

    suspend fun changeUserStatus(uuid: String, userStatus: UserStatus) {
        dao.updateStatus(uuid, userStatus)
    }

    companion object {
        const val TAG = "MainUserRepository"
    }
}