package com.kotlinblog.dontgetfat.main.calories

import android.arch.lifecycle.ViewModel
import timber.log.Timber

class CaloriesViewModel : ViewModel() {

    private var testValue: String = "testValue"

    fun change() {
        testValue = "changedTestValue"
    }

    fun log() {
        Timber.d("value: $testValue")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }
}