package com.kotlinblog.dontgetfat.data.database.dbutils

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * TODO: Add a class header comment!
 */
object DbDateConverter {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? = if (null == value) null else Date(value)

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? = date?.time
}