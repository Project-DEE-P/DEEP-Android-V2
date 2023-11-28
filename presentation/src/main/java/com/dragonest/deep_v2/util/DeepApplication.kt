package com.dragonest.deep_v2.util

import android.app.Application
import android.graphics.Bitmap
import com.zynksoftware.documentscanner.ui.DocumentScanner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeepApplication : Application() {

    override fun onCreate() {

        val configuration = DocumentScanner.Configuration()
        configuration.imageQuality = 100
        configuration.imageSize = 1000000 // 1 MB
        configuration.imageType = Bitmap.CompressFormat.JPEG
        DocumentScanner.init(this, configuration) // or simply DocumentScanner.init(this)

        super.onCreate()
    }


    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        prefs = PreferenceManager(applicationContext)
        super.onCreate()
    }


}