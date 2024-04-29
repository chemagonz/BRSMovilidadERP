package com.advantys.brsmovilidaderp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BRSMovilidadERPApp:Application() {
    companion object {
        private val sApplication: Application? = null
        fun getApplication(): Application? {
            return sApplication
        }

        fun getContext(): Context {
            return getApplication()!!.applicationContext
        }
    }
}