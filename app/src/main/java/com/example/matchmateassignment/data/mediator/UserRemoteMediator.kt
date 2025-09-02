package com.example.matchmateassignment.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.matchmateassignment.data.local.UserProfileDataBase
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.remote.RemoteDataApi
import com.example.matchmateassignment.data.remote.UserProfileData.Companion.toDbList

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val remoteDataApi: RemoteDataApi,
    private val userDatabase: UserProfileDataBase
) : RemoteMediator<Int, UserProfileDbData>() {

    private val userDao = userDatabase.userProfileDao()
    private val remoteKeyDao = userDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserProfileDbData>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

//            if (page == 2) {
//                throw RuntimeException("Error occurred on page $page")
//            }

//            if (page == 1) {
//                return MediatorResult.Success(endOfPaginationReached = false)
//            }

            val response = remoteDataApi.getUserList(page = page, results = state.config.pageSize)
            val users = response.results.toDbList()
            val endOfPagination = users.isEmpty()

            userDatabase.withTransaction {
                if (loadType == LoadType.REFRESH && users.isNotEmpty()) { // not consider in our case we want same data generation
                    remoteKeyDao.clearRemoteKeys()
                }

                val keys = users.map {
                    UserRemoteKeys(
                        uuid = it.uuid,
                        prevPage = if (page == 1) null else page - 1,
                        nextPage = if (endOfPagination) null else page + 1
                    )
                }
                userDatabase.remoteKeyDao().insertAllRemoteKeys(keys)
                userDatabase.userProfileDao().insertUsers(users)
            }

            return MediatorResult.Success(endOfPagination)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserProfileDbData>): UserRemoteKeys? {
        return state.pages.lastOrNull()?.data?.lastOrNull()?.let {
            remoteKeyDao.getRemoteKey(it.uuid)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserProfileDbData>): UserRemoteKeys? {
        return state.pages.firstOrNull()?.data?.firstOrNull()?.let {
            remoteKeyDao.getRemoteKey(it.uuid)
        }
    }
}