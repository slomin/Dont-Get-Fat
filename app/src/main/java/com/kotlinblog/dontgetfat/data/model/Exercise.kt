package com.kotlinblog.dontgetfat.data.model

import android.arch.persistence.room.*
import java.util.*

/**
 * TODO: Add a class header comment!
 */
@Entity(tableName = "exercises",
        indices = arrayOf(Index(value = "day_id")),
        foreignKeys = arrayOf(
                ForeignKey(entity = Day::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("day_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)))
data class Exercise(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long = 0,

        @ColumnInfo(name = "day_id")
        val dayId: Long,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "calories")
        val calories: Int

)