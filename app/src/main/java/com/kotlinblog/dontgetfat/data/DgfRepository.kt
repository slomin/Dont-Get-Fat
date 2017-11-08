package com.kotlinblog.dontgetfat.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.format.DateUtils
import com.kotlinblog.dontgetfat.App
import com.kotlinblog.dontgetfat.data.database.DgfDatabase
import com.kotlinblog.dontgetfat.data.database.entity.Day
import com.kotlinblog.dontgetfat.data.database.entity.DayWithMeals
import com.kotlinblog.dontgetfat.data.database.entity.Exercise
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import com.kotlinblog.dontgetfat.temp.Constants
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DgfRepository {

    @Inject lateinit var mDb: DgfDatabase

    private var mIsDbBeingAccessed: MutableLiveData<Boolean>
    val isDbBeingAccessed: LiveData<Boolean> get() = mIsDbBeingAccessed

    init {
        App.component.inject(this)
        mIsDbBeingAccessed = MutableLiveData()
        mIsDbBeingAccessed.postValue(false)
        Timber.d("DgfRepository INIT")
    }

    fun getMealsByDayId(id: Long): LiveData<List<Meal>> {
        Timber.d("!!!!")
        return mDb.mealsDao().getAllMealsForGivenDay(id)!!
    }

    fun addMeal(calories: Int) {
        doAsync {
            mIsDbBeingAccessed.postValue(true)
            val currentDate = Date()
            var lastRecordedDay = mDb.daysDao().lastDay
            var lastRecordedDayId = lastRecordedDay?.id
            //If it is first record or first record on a given day - create and store new Day
            if (lastRecordedDay == null || !DateUtils.isToday(lastRecordedDay.date.time)) {
                lastRecordedDay = Day(date = currentDate, caloriesAllowed = Constants.CALORIES_ALLOWED)
                lastRecordedDayId = mDb.daysDao().insertDay(lastRecordedDay) //inserting Day and storing it's ID
            }
            val newMeal = Meal(dayId = lastRecordedDayId!!, date = currentDate, calories = calories)
            val newMealId = mDb.mealsDao().insertMeal(newMeal)
            Timber.d("meal ID: $newMealId")
            mIsDbBeingAccessed.postValue(false)

        }

    }

    fun deleteMeal(meal: Meal) {
        doAsync { mDb.mealsDao().deleteMeal(meal) }
    }

    fun updateMeal(meal: Meal) {
        doAsync { mDb.mealsDao().updateMeal(meal) }
    }

    fun addExercise(calories: Int, isFromSteps: Boolean = false) {
        doAsync {
            val currentDate = Date()
            var lastRecordedDay = mDb.daysDao().lastDay
            var lastRecordedDayId = lastRecordedDay?.id
            //If it is first record or first record on a given day - create and store new Day
            if (lastRecordedDay == null || !DateUtils.isToday(lastRecordedDay.date.time)) {
                lastRecordedDay = Day(date = currentDate, caloriesAllowed = Constants.CALORIES_ALLOWED)
                lastRecordedDayId = mDb.daysDao().insertDay(lastRecordedDay) //inserting Day and storing it's ID
            }
            val newExercise = Exercise(dayId = lastRecordedDayId!!, date = currentDate, calories = calories, isFromSteps = isFromSteps)
            val newExerciseId = mDb.activitiesDao().insertActivity(newExercise)
            Timber.d("exercise ID: $newExerciseId")

        }
    }

    fun getTodayCaloriesConsumed(): Int {
        var caloriesConsumed = 0
        doAsync {
            val meals = mDb.mealsDao().getAllMealsForGivenDay(1)
//            for (meal in meals!!) {
//                caloriesConsumed += meal.calories
//                Timber.d("Meal ID: ${meal.id}")
//                Timber.d("Meal calories: ${meal.calories}")
//            }
            uiThread {

                Timber.d("Calories consumed: $caloriesConsumed")
            }
        }
        return caloriesConsumed
    }

    fun getLastDayWithMeals(): LiveData<DayWithMeals> {
        return mDb.dayWithMealsDao().lastDayWithMealsLiveData
    }

}