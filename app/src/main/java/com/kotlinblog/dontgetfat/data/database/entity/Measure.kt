package com.kotlinblog.dontgetfat.data.database.entity

import android.arch.persistence.room.*
import java.util.*

/**
 * TODO: Add a class header comment!
 */
@Entity(tableName = "measures",
        indices = arrayOf(Index(value = "day_id", unique = true)), // only one measure per day
        foreignKeys = arrayOf(
                ForeignKey(entity = Day::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("day_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)))
data class Measure(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = 0,

        @ColumnInfo(name = "day_id")
        val dayId: Long,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "weight") // Weight in grams
        val weight: Int,

        @ColumnInfo(name = "waist_size") //Waist size in cm
        val waistSize: Int

)