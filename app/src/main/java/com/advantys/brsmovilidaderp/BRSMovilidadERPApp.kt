package com.advantys.brsmovilidaderp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BRSMovilidadERPApp:Application() {
    companion object {
        private var sApplication: Application? = null
        fun getApplication(): Application? {
            return sApplication
        }

        fun getContext(): Context {
            return getApplication()!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }

}