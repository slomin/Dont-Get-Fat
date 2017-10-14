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
        val id: Long,

        @ColumnInfo(name = "date")
        val date: Date

)