package com.kotlinblog.dontgetfat

import com.kotlinblog.dontgetfat.data.database.entity.Day
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import java.util.*

class TestUtil {
    companion object {
        fun createDay(date: Date): Day {
            return Day(date = date, caloriesAllowed = 2000)
        }

        fun createMeals(dayId: Long, numberOfMeals: Int = 5): List<Meal> {
            return (1..numberOfMeals)
                    .map { Meal(dayId = dayId, date = Date(), calories = 300 + it * 50) }
                    .toMutableList()
        }
    }
}