package com.dragonest.deep_v2.util

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeepApplication : Application() {

    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        prefs = PreferenceManager(applicationContext)
        super.onCreate()
    }

}