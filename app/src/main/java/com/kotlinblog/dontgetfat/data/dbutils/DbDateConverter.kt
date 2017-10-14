package com.kotlinblog.dontgetfat.data.dbutils

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * TODO: Add a class header comment!
 */
object DbDateConverter {
    @TypeConverter
    @JvmStatic
    fun fromTimestampToDate(value: Long?): Date? = if (null == value) null else Date(value)

    @TypeConverter
    @JvmStatic
    fun fromDateToTimestamp(date: Date?): Long? = date?.time
}