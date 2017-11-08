package com.kotlinblog.dontgetfat.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * TODO: Add a class header comment!
 */
@Entity(tableName = "meals")
data class Meal(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = 0,

        @ColumnInfo(name = "day_id")
        val dayId: Long,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "calories")
        var calories: Int
)