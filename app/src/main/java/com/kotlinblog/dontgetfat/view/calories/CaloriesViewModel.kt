package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.kotlinblog.dontgetfat.DgfApp
import com.kotlinblog.dontgetfat.data.DgfRepository
import com.kotlinblog.dontgetfat.data.DgfRepositoryObserver
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class CaloriesViewModel : ViewModel(), DgfRepositoryObserver {

    @Inject lateinit var mRepository: DgfRepository
    private val mObserver: Observer<Boolean>

    init {
        DgfApp.component.inject(this)
        mObserver = Observer { isDbBeingAccessed ->
            Timber.d("isDbBeingAccessed changed: $isDbBeingAccessed")
        }
        observeRepo()
    }

    private var testValue: String = "testValue"

    private var entriesMap: MutableMap<Date, Int> = hashMapOf()

    override fun observeRepo() {
        mRepository.isDbBeingAccessed.observeForever(mObserver)
    }

    override fun stopObservingRepo() {
        mRepository.isDbBeingAccessed.removeObserver { mObserver }
    }

    fun log() {
        mRepository.addMeal(100)
    }

    fun addCalories(date: Date, calories: Int) {
        entriesMap.put(date, calories)
    }

    override fun onCleared() {
        super.onCleared()
        stopObservingRepo()
        Timber.d("onCleared")
    }

    fun test() {
        mRepository.getTodayCaloriesConsumed()
    }





}