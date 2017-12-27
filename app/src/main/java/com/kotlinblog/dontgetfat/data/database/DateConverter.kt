package com.kotlinblog.dontgetfat.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Converts from Date object to Long and vice versa (for Room ORM to store date in SQLite as Long)
 */
object DateConverter {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? = if (null == value) null else Date(value)

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? = date?.time
}