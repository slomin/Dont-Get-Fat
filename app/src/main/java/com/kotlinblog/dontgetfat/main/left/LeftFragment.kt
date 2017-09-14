package com.kotlinblog.dontgetfat.main.left

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kotlinblog.dontgetfat.R
import kotlinx.android.synthetic.main.fragment_left.*

class LeftFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_left, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity).get(LeftViewModel::class.java)

        btnChange.setOnClickListener { viewModel.change() }
        btnLog.setOnClickListener { viewModel.log() }
    }


}
