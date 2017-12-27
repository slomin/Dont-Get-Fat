package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.kotlinblog.dontgetfat.App
import com.kotlinblog.dontgetfat.data.DgfRepository
import com.kotlinblog.dontgetfat.data.DgfRepositoryObserver
import com.kotlinblog.dontgetfat.data.database.entity.DayWithMeals
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CaloriesViewModel : ViewModel(), DgfRepositoryObserver {

    @Inject lateinit var mRepository: DgfRepository
    private val mObserver: Observer<Boolean>

    private var mDayWithMeals: LiveData<DayWithMeals>
    val dayWithMeals: LiveData<DayWithMeals> get() = mDayWithMeals

    var mMealToEdit: Meal? = null

    init {
        App.component.inject(this)
        mObserver = Observer { isDbBeingAccessed ->
            Timber.d("isDbBeingAccessed changed: $isDbBeingAccessed")
        }
        observeRepo()
        mDayWithMeals = mRepository.getLastDayWithMeals()
    }

    override fun observeRepo() {
        mRepository.isDbBeingAccessed.observeForever(mObserver)
    }

    override fun stopObservingRepo() {
        mRepository.isDbBeingAccessed.removeObserver { mObserver }
    }


    fun addCalories(calories: Int) {
        mRepository.addMeal(calories)
    }

    override fun onCleared() {
        super.onCleared()
        stopObservingRepo()
        Timber.d("onCleared")
    }


    fun getMealTime(date: Date): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }

    fun deleteMeal(meal: Meal) {
        mRepository.deleteMeal(meal)
        Timber.d("Removing meal: $meal")
    }

    fun editMeal(calories: Int) {
        mMealToEdit?.let {
            it.calories = calories
            mRepository.updateMeal(it)
            Timber.d("Editing meal: $it")
        }
        mMealToEdit = null
    }

    /**
     * Adapter callbacks
     */
    fun getMealAt(position: Int): Meal? {
        return if (position < getMealListSize()) {
            dayWithMeals.value?.meals?.get(position)
        } else {
            null
        }
    }

    fun getMealListSize(): Int {
        dayWithMeals.value?.meals?.let{
            return it.size
        }
        return 0
    }

}