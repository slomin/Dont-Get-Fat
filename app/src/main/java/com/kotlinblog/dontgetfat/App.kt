package com.kotlinblog.dontgetfat

import android.app.Application
import com.kotlinblog.dontgetfat.di.AppComponent
import com.kotlinblog.dontgetfat.di.AppModule
import com.kotlinblog.dontgetfat.di.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class App : Application() {

    companion object {
        private lateinit var mComponent: AppComponent
        val component: AppComponent get() = mComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initializeTimber()
        initializeLeakCanary()
    }

    private fun initializeDagger() {
        mComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initializeTimber() {
        // Timber now logs line numbers by default + convenience TAG added
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "Timber - " + super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        }
    }

    private fun initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}