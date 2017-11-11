package com.kotlinblog.dontgetfat.dao

import android.support.test.runner.AndroidJUnit4
import com.kotlinblog.dontgetfat.TestUtil
import com.kotlinblog.dontgetfat.extensions.getEndOfDay
import com.kotlinblog.dontgetfat.extensions.getStartOfDay
import com.kotlinblog.dontgetfat.extensions.getValueBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
open class DayWithMealsDaoTest : AbstractDbTest() {
    @Test
    fun getCurrentDayWithMealsTest() {
        val now = Date()
        val currentMealAsLiveData = dgfDatabase
                .dayWithMealsDao()
                .getCurrentDayWithMeals(now.getStartOfDay(), now.getEndOfDay())
        //Should return LiveData
        assertNotNull(currentMealAsLiveData)

        val day = TestUtil.createDay(now)
        val dayId = dgfDatabase.daysDao().insertDay(day)

        val meals = TestUtil.createMeals(dayId = dayId, numberOfMeals = 8)

        for (meal in meals) {
            dgfDatabase.mealsDao().insertMeal(meal)
        }

        val dayWithMeals = currentMealAsLiveData.getValueBlocking()

        //Asserting if timestamp is stored properly
        assertEquals(dayWithMeals?.day?.date?.time, now.time)

        //Asserting stored meals have the same calories and timestamp
        for (i in 0..(meals.size - 1)) {
            if (dayWithMeals != null) {
                assertEquals(meals[i].calories, dayWithMeals.meals[i].calories)
                assertEquals(meals[i].date.time, dayWithMeals.meals[i].date.time)
            } else {
                fail("dayWithMeals is null...")
            }
        }
    }
}