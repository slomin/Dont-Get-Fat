package com.kotlinblog.dontgetfat.dao

import android.support.test.runner.AndroidJUnit4
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
open class MealsDaoTest : AbstractDbTest() {

    @Test
    fun insertMealTest() {
        val meal = Meal(
                dayId = 1,
                date = Date(),
                calories = 500)
        dgfDatabase.mealsDao().insertMeal(meal)
        val lastMealFromDb = dgfDatabase.mealsDao().lastMeal

        // Asserting if there is a Meal
        assertNotNull(lastMealFromDb)

        // Asserting if Meal has auto-generated id value
        assert(lastMealFromDb?.id == 1L)

        // Asserting that Meal was inserted into DB
        assert(lastMealFromDb?.equals(meal)!!)
    }

    @Test
    fun updateMealTest() {
        val newCalories = 100
        val meal = Meal(
                dayId = 1,
                date = Date(),
                calories = 500)

        dgfDatabase.mealsDao().insertMeal(meal)

        val currentMealFromDb = dgfDatabase.mealsDao().lastMeal

        currentMealFromDb?.calories = newCalories
        dgfDatabase.mealsDao().updateMeal(currentMealFromDb)
        val currentMealFromDbAfterUpdate = dgfDatabase.mealsDao().lastMeal

        //Asserting that meal has been changed
        assert(!currentMealFromDb?.equals(currentMealFromDbAfterUpdate)!!)

        //Asserting that new calories value is equal to "new calories"
        assert(currentMealFromDbAfterUpdate?.calories == newCalories)
    }

    @Test
    fun deleteMealTest() {
        val meal = Meal(
                dayId = 1,
                date = Date(),
                calories = 500)

        dgfDatabase.mealsDao().insertMeal(meal)

        val lastMealFromDb = dgfDatabase.mealsDao().lastMeal

        // Asserting that Meal was inserted into DB
        assert(lastMealFromDb?.equals(meal)!!)

        dgfDatabase.mealsDao().deleteMeal(lastMealFromDb)

        val lastMealFromDbAfterDeleting = dgfDatabase.mealsDao().lastMeal

        //Asserting Meal was successfully removed
        assertNull(lastMealFromDbAfterDeleting)
    }

    
}