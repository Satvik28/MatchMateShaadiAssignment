package com.example.matchmateassignment.di

import android.content.Context
import androidx.room.Room
import com.example.matchmateassignment.data.local.UserProfileDataBase
import com.example.matchmateassignment.data.local.UserProfileDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataBaseModule {

    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext applicationContext: Context
    ): UserProfileDataBase {
        return Room.databaseBuilder(
            applicationContext,
            UserProfileDataBase::class.java,
            "user_profile_db"
        ).build()
    }

    @Provides
    @Singleton
    fun getUserProfileDao(userDatabase: UserProfileDataBase): UserProfileDbDao {
        return userDatabase.userProfileDao()
    }
}