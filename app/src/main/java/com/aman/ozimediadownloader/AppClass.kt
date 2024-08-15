package com.aman.ozimediadownloader

import android.app.Application
import com.aman.downloader.OziDownloader

class AppClass: Application() {

    lateinit var oziDownloader: OziDownloader

    override fun onCreate() {
        super.onCreate()

        oziDownloader = OziDownloader.create(applicationContext)
    }
}