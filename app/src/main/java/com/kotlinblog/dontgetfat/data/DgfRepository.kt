package com.kotlinblog.dontgetfat.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.format.DateUtils
import com.kotlinblog.dontgetfat.DgfApp
import com.kotlinblog.dontgetfat.data.database.DgfDatabase
import com.kotlinblog.dontgetfat.data.model.Day
import com.kotlinblog.dontgetfat.data.model.Exercise
import com.kotlinblog.dontgetfat.data.model.Meal
import com.kotlinblog.dontgetfat.temp.Constants
import kotlinx.coroutines.experimental.launch
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
        DgfApp.component.inject(this)
        mIsDbBeingAccessed = MutableLiveData()
        mIsDbBeingAccessed.postValue(false)
        Timber.d("DgfRepository INIT")
    }

    fun getMealsByDayId(id: Long): LiveData<List<Meal>> {
        Timber.d("!!!!")
        return mDb.mealsDao().getAllMealsForGivenDay(id)!!
    }
    fun addMeal(calories: Int) {
        launch {
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

    fun addExercise(calories: Int) {
        doAsync {
            val currentDate = Date()
            var lastRecordedDay = mDb.daysDao().lastDay
            var lastRecordedDayId = lastRecordedDay?.id
            //If it is first record or first record on a given day - create and store new Day
            if (lastRecordedDay == null || !DateUtils.isToday(lastRecordedDay.date.time)) {
                lastRecordedDay = Day(date = currentDate, caloriesAllowed = Constants.CALORIES_ALLOWED)
                lastRecordedDayId = mDb.daysDao().insertDay(lastRecordedDay) //inserting Day and storing it's ID
            }
            val newExercise = Exercise(dayId = lastRecordedDayId!!, date = currentDate, calories = calories)
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

//    fun addMeal2(calories: Int) {
//        doAsync {
//            val lastDay = mDb.daysDao().lastDay
//            Timber.d("last day id: " + lastDay?.id)
//            Timber.d("last day date: " + lastDay?.date)
//            val currentDate = Date()
//            if (lastDay == null || !DateUtils.isToday(lastDay.date.time)) {
//                val newDay = Day(date = currentDate)
//                val newDayId = mDb.daysDao().insertDay(newDay) //inserting Day and storing it's ID
//                val newMeal = Meal(dayId = newDayId, date = currentDate, calories =  calories)
//                val newMealId = mDb.mealsDao().insertMeal(newMeal)
//                Timber.d("New Meal ID: " + newMealId)
//            } else {
//                val newMeal = Meal(dayId = lastDay.id, date = currentDate, calories = calories)
//                val newMealId = mDb.mealsDao().insertMeal(newMeal)
//                Timber.d("New Meal ID: " + newMealId)
//            }
//            val lastMeal = mDb.mealsDao().lastMeal
//            Timber.d("last meal...")
//        }
//    }


}