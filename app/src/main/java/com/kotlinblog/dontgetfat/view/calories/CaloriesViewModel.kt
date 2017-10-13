package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.ViewModel
import timber.log.Timber
import java.util.*

class CaloriesViewModel : ViewModel() {


    private var testValue: String = "testValue"

    private var entriesMap: MutableMap<Date, Int> = hashMapOf()

    fun log() {
        Timber.d("value: ${entriesMap}")
    }

    fun addCalories(date: Date, calories: Int) {
        entriesMap.put(date, calories)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}