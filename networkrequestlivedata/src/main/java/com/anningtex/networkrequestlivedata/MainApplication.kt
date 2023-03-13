package com.anningtex.networkrequestlivedata

import android.app.Application
import android.annotation.SuppressLint
import android.content.Context

/**
 * @author Song
 */
class MainApplication : Application() {
    @SuppressLint("NewApi")
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }
}