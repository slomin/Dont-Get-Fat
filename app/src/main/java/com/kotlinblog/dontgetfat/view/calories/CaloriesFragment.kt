package com.kotlinblog.dontgetfat.view.calories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codetroopers.betterpickers.numberpicker.NumberPickerBuilder
import com.codetroopers.betterpickers.numberpicker.NumberPickerDialogFragment
import com.kotlinblog.dontgetfat.R
import com.kotlinblog.dontgetfat.data.database.entity.Meal
import com.kotlinblog.dontgetfat.temp.Constants
import kotlinx.android.synthetic.main.fragment_calories.*
import timber.log.Timber
import java.math.BigDecimal
import java.math.BigInteger

class CaloriesFragment : Fragment(), NumberPickerDialogFragment.NumberPickerDialogHandlerV2 {

    private lateinit var mViewModel: CaloriesViewModel
    private lateinit var mAdapter: CaloriesAdapter

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_calories, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProviders.of(activity).get(CaloriesViewModel::class.java)
        mAdapter = CaloriesAdapter(mViewModel, context)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }

        // Setting click listener for calories add dialog
        btnAddCalories.setOnClickListener {
            val numberPickerBuilder = NumberPickerBuilder()
                    .setPlusMinusVisibility(View.GONE)
                    .setDecimalVisibility(View.GONE)
                    .setMaxNumber(BigDecimal(Constants.CALORIES_ALLOWED.toString()))
                    .setTargetFragment(this)
                    .setFragmentManager(fragmentManager)
                    .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                    .setLabelText(getString(R.string.kcal))
            numberPickerBuilder.show()
        }

        observeMeals()

    }

    /**
     * Listener for NumberPicker responsible for adding calories
     * @param number = calories to add
     */
    override fun onDialogNumberSet(reference: Int, number: BigInteger?, decimal: Double, isNegative: Boolean, fullNumber: BigDecimal?) {
        Timber.d("value set: $number")
        val viewModel = ViewModelProviders.of(activity).get(CaloriesViewModel::class.java)
        if (number != null && number.toInt() != 0) {
            viewModel.addCalories(number.toInt())
        }
    }

    private fun observeMeals() {
        val mealsObserver = Observer<List<Meal>> { meals ->
            if (meals != null) {
                var totalCalories = 0
                for (meal in meals) {
                    totalCalories += meal.calories
                }
                setConsumptionViews(totalCalories)
            }
            mAdapter.notifyDataSetChanged()
        }
        val viewModel = ViewModelProviders.of(activity).get(CaloriesViewModel::class.java)
        viewModel.meals.observe(this, mealsObserver)

    }

    private fun setConsumptionViews(calories: Int) {
        tvCaloriesConsumed.text = getString(R.string.current_consumption, calories, Constants.CALORIES_ALLOWED)
        val caloriesLeft = Constants.CALORIES_ALLOWED - calories
        tvCaloriesLeft.text = caloriesLeft.toString()
    }

}
