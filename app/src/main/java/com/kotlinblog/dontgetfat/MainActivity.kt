package com.kotlinblog.dontgetfat

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_left_fragment -> {
                message.setText(R.string.title_left_fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_middle_fragment -> {
                message.setText(R.string.title_middle_fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_right_fragment -> {
                message.setText(R.string.title_right_fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
