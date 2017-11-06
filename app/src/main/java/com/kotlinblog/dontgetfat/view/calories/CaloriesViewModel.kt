package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.kotlinblog.dontgetfat.App
import com.kotlinblog.dontgetfat.data.DgfRepository
import com.kotlinblog.dontgetfat.data.DgfRepositoryObserver
import com.kotlinblog.dontgetfat.data.model.Meal
import timber.log.Timber
import javax.inject.Inject

class CaloriesViewModel : ViewModel(), DgfRepositoryObserver {

    @Inject lateinit var mRepository: DgfRepository
    private val mObserver: Observer<Boolean>

    private var mMeals: LiveData<List<Meal>>
    val meals: LiveData<List<Meal>> get() = mMeals

    init {
        App.component.inject(this)
        mMeals = mRepository.getMealsByDayId(1)
        mObserver = Observer { isDbBeingAccessed ->
            Timber.d("isDbBeingAccessed changed: $isDbBeingAccessed")
        }
        observeRepo()
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


    /**
     * Adapter callbacks
     */
    fun getMealAt(position: Int): Meal? {
        return if (position < getMealListSize()) {
            mMeals.value?.get(position)
        } else {
            null
        }
    }

    fun getMealListSize(): Int {
        mMeals.value?.let {
            return it.size
        }
        return 0
    }

}