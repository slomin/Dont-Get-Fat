package com.kotlinblog.dontgetfat.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.kotlinblog.dontgetfat.App
import com.kotlinblog.dontgetfat.data.database.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val context: Context) {
    @Provides
    @Singleton
    fun providesApplication(application: App): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesDatabase(): MyDatabase {
        return Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                MyDatabase.DATABASE_FILE_NAME)
                .build()
    }
}