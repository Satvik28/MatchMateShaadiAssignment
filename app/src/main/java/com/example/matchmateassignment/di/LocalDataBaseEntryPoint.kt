package com.example.matchmateassignment.di

import android.content.Context
import com.example.matchmateassignment.utils.IMatchScore
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocalDataBaseEntryPoint {

    fun getMatchScore() : IMatchScore

    companion object {
        fun getMatchScore(context: Context): IMatchScore = EntryPointAccessors.fromApplication(context, LocalDataBaseEntryPoint::class.java).getMatchScore()

    }
}