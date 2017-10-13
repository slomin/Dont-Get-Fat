package com.kotlinblog.dontgetfat.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.kotlinblog.dontgetfat.R
import com.kotlinblog.dontgetfat.extensions.addFragment
import com.kotlinblog.dontgetfat.extensions.replaceFragment
import com.kotlinblog.dontgetfat.view.calories.CaloriesFragment
import com.kotlinblog.dontgetfat.view.history.HistoryFragment
import com.kotlinblog.dontgetfat.view.weight.WeightFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mLeftFragment = CaloriesFragment()
    private val mMiddleFragment = WeightFragment()
    private val mRightFragment = HistoryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(mLeftFragment, R.id.fragmentContainer)
        setSupportActionBar(toolbar)
        bottomNavBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_left_fragment -> {
                replaceFragment(mLeftFragment, R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_middle_fragment -> {
                replaceFragment(mMiddleFragment, R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_right_fragment -> {
                replaceFragment(mRightFragment, R.id.fragmentContainer)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
