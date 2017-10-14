package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.ViewModel
import com.kotlinblog.dontgetfat.App
import com.kotlinblog.dontgetfat.data.database.MyDatabase
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class CaloriesViewModel : ViewModel() {

    @Inject lateinit var mDb: MyDatabase

    init {
        App.component.inject(this)
    }

    private var testValue: String = "testValue"

    private var entriesMap: MutableMap<Date, Int> = hashMapOf()

    fun log() {
        Timber.d("value: ${entriesMap}")
        Timber.d("db: ${mDb}")
    }

    fun addCalories(date: Date, calories: Int) {
        entriesMap.put(date, calories)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }

}