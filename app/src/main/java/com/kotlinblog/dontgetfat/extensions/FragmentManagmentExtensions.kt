package com.kotlinblog.dontgetfat.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.kotlinblog.dontgetfat.temp.AppCompatLifecycleActivity

/**
 * All the extensions helping fragment management
 *
 * This particular elegant solution explained here:
 * https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b
 */


fun AppCompatLifecycleActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatLifecycleActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

internal inline fun FragmentManager.inTransaction(func: FragmentTransaction.
() -> FragmentTransaction) {
    beginTransaction().func().commit()
}