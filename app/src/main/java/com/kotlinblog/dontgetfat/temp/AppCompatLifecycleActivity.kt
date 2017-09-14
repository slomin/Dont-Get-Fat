package com.kotlinblog.dontgetfat.temp

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity


/**
 * TODO: Temporary custom LifecycleActivity - will be removed after Architecture Components will reach v1.0
 *
 * Note: Since the Architecture Components are in alpha stage, Fragment and AppCompatActivity classes cannot implement it (because we cannot add a dependency from a stable component to an unstable API). Until Lifecycle is stable, LifecycleActivity and LifecycleFragment classes are provided for convenience. After the Lifecycles project is released, support library fragments and activities will implement the LifecycleOwner interface; LifecycleActivity and LifecycleFragment will be deprecated at that time.
 *
 * https://stackoverflow.com/questions/44664388/how-to-setsupportactionbar-in-a-view-that-extends-lifecycleactivity
 */

abstract class AppCompatLifecycleActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val mRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }
}