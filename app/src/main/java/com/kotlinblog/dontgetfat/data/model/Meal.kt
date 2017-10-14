package com.kotlinblog.dontgetfat.data.model

import android.arch.persistence.room.*
import java.util.*

/**
 * TODO: Add a class header comment!
 */
@Entity(tableName = "meals",
        indices = arrayOf(Index(value = "day_id", unique = true)), //Todo not sure if unique
        foreignKeys = arrayOf(
                ForeignKey(entity = Day::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("day_id"),
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)))
data class Meal(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long,

        @ColumnInfo(name = "day_id")
        val dayId: Long,

        @ColumnInfo(name = "date")
        val date: Date,

        @ColumnInfo(name = "calories")
        val calories: Int

)