package com.kotlinblog.dontgetfat.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * TODO: Add a class header comment!
 */
@Entity(tableName = "days")
data class Day(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "calories_allowed")
        val caloriesAllowed: Int

)