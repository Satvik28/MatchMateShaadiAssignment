package com.example.matchmateassignment.di

import android.content.Context
import androidx.room.Room
import com.example.matchmateassignment.data.local.MyUser
import com.example.matchmateassignment.data.local.UserProfileDataBase
import com.example.matchmateassignment.data.local.UserProfileDbDao
import com.example.matchmateassignment.data.mediator.RemoteKeyDao
import com.example.matchmateassignment.utils.IMatchScore
import com.example.matchmateassignment.utils.MatchScore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataBaseModule {

    @Binds
    abstract fun getMatchScore(matchScore: MatchScore): IMatchScore

    companion object {
        @Provides
        @Singleton
        fun getDatabase(
            @ApplicationContext applicationContext: Context
        ): UserProfileDataBase {
            return Room.databaseBuilder(
                applicationContext,
                UserProfileDataBase::class.java,
                "user_database"
            ).build()
        }

        @Provides
        @Singleton
        fun getUserProfileDao(userDatabase: UserProfileDataBase): UserProfileDbDao {
            return userDatabase.userProfileDao()
        }

        @Provides
        @Singleton
        fun getRemoteKeyDao(userDatabase: UserProfileDataBase): RemoteKeyDao {
            return userDatabase.remoteKeyDao()
        }

        @Provides
        @Singleton
        fun getMyUserObject() = MyUser.INSTANCE

    }
}