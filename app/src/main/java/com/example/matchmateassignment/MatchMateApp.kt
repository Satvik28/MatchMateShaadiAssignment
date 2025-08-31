package com.example.matchmateassignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.rotbolt.flakerandroidokhttp.di.FlakerAndroidOkhttpContainer

@HiltAndroidApp
class MatchMateApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FlakerAndroidOkhttpContainer.install(this)
    }
}
