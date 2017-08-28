package com.kotlinblog.dontgetfat.application

import android.app.Application
import com.kotlinblog.dontgetfat.BuildConfig
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        // Timber now logs line numbers by default + convenience TAG added
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "Timber - " + super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }
}