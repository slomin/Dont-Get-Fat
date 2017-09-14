package com.kotlinblog.dontgetfat.main.left

import android.arch.lifecycle.ViewModel
import timber.log.Timber

class LeftViewModel : ViewModel() {

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