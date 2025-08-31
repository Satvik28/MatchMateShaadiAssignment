package com.example.matchmateassignment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.matchmateassignment.data.local.UserProfileDataBase
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.data.mediator.UserRemoteMediator
import com.example.matchmateassignment.data.remote.UserApi
import com.example.matchmateassignment.utils.AppConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MainUserRepository @Inject constructor(
    private val api: UserApi,
    private val userProfileDataBase: UserProfileDataBase
) {
    val dao = userProfileDataBase.userProfileDao()

    fun getUsers(): Flow<PagingData<UserProfileDbData>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConstants.REMOTE_API_RESULTS,
                prefetchDistance = AppConstants.DATA_LOAD_THRESHOLD,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(userApi = api, userDatabase = userProfileDataBase),
            pagingSourceFactory = { dao.getAllUsersWithPaging() }
        ).flow
    }

    suspend fun changeUserStatus(uuid: String, userStatus: UserStatus) {
        dao.updateStatus(uuid, userStatus)
    }

    companion object {
        const val TAG = "MainUserRepository"
    }
}