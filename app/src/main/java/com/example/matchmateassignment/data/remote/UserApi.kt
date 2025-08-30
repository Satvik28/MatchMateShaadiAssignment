package com.example.matchmateassignment.data.remote

import com.example.matchmateassignment.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/")
    suspend fun getUserList(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = AppConstants.REMOTE_API_RESULTS,
        @Query("seed") seed: String = AppConstants.REMOTE_API_SEED,
        @Query("inc") inc: String = AppConstants.REMOTE_API_INCLUDED_FIELDS,
        @Query("nat") nat: String = AppConstants.REMOTE_API_NATIONALITY
    ): ProfileDataResponse
}