package com.kotlinblog.dontgetfat.data.database.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import java.util.Collections.emptyList

/**
 * TODO: Add a class header comment!
 */
data class DayWithMeals(
        @Embedded var day: Day?,
        @Relation(parentColumn = "id", entityColumn = "day_id")
        var meals: List<Meal>
) {
    constructor() : this(day = null, meals = emptyList())
}