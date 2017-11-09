package com.kotlinblog.dontgetfat.Dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.kotlinblog.dontgetfat.data.database.DgfDatabase
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
open class MealsDaoTests {

    private lateinit var dgfDatabase: DgfDatabase


    @Before
    fun initDb() {
        dgfDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                DgfDatabase::class.java)
                .build()

    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        dgfDatabase.close()
    }

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