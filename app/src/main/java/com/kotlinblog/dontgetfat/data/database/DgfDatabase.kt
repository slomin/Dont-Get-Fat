package com.kotlinblog.dontgetfat.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.kotlinblog.dontgetfat.data.database.dao.ActivitiesDao
import com.kotlinblog.dontgetfat.data.database.dao.DaysDao
import com.kotlinblog.dontgetfat.data.database.dao.MealsDao
import com.kotlinblog.dontgetfat.data.database.entity.Day
import com.kotlinblog.dontgetfat.data.database.entity.Exercise
import com.kotlinblog.dontgetfat.data.database.entity.Meal

@Database(entities = arrayOf(
        Day::class,
        Meal::class,
        Exercise::class),
        version = 1,
        exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class DgfDatabase : RoomDatabase() {
    abstract fun daysDao(): DaysDao
    abstract fun mealsDao(): MealsDao
    abstract fun activitiesDao(): ActivitiesDao

    companion object {
        val DATABASE_FILE_NAME = "dont_get_fat.db"
    }
}