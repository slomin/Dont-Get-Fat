package com.kotlinblog.dontgetfat.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.kotlinblog.dontgetfat.DgfApp
import com.kotlinblog.dontgetfat.data.DgfRepository
import com.kotlinblog.dontgetfat.data.database.DgfDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val context: Context) {
    @Provides
    @Singleton
    fun providesApplication(application: DgfApp): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesDatabase(): DgfDatabase {
        return Room.databaseBuilder(
                context,
                DgfDatabase::class.java,
                DgfDatabase.DATABASE_FILE_NAME)
                .build()
    }

    @Provides
    @Singleton
    fun providesRepository(): DgfRepository {
        return DgfRepository()
    }
}