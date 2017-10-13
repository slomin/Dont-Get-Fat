package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment
import com.kotlinblog.dontgetfat.R
import kotlinx.android.synthetic.main.fragment_calories.*
import timber.log.Timber
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*


class CaloriesFragment : Fragment(), NumberPickerDialogFragment.NumberPickerDialogHandlerV2 {

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_calories, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(activity).get(CaloriesViewModel::class.java)

//        btnChange.setOnClickListener { viewModel.change() }
        btnLog.setOnClickListener { viewModel.log() }

        // Setting click listener for calories add dialog
        btnAddCalories.setOnClickListener {
            val npb = NumberPickerBuilder()
                    .setPlusMinusVisibility(View.GONE)
                    .setDecimalVisibility(View.GONE)
                    .setMaxNumber(BigDecimal("2000"))
                    .setTargetFragment(this)
                    .setFragmentManager(fragmentManager)
                    .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                    .setLabelText(getString(R.string.kcal))
            npb.show()
        }

    }

    /**
     * Listener for NumberPicker responsible for adding calories
     * @param number = calories to add
     */
    override fun onDialogNumberSet(reference: Int, number: BigInteger?, decimal: Double, isNegative: Boolean, fullNumber: BigDecimal?) {
        Timber.d("value set: $number")
        val viewModel = ViewModelProviders.of(activity).get(CaloriesViewModel::class.java)
        val currentTime = Date()
        if (number != null && number.toInt() != 0) {
            viewModel.addCalories(currentTime, number.toInt())
        }
    }
}
