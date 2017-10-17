package com.kotlinblog.dontgetfat.view.calories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinblog.dontgetfat.R
import timber.log.Timber

class CaloriesAdapter(private var viewModel: CaloriesViewModel)
    : RecyclerView.Adapter<CaloriesAdapter.MealsViewHolder>() {

//    private var onBottomReachedListener: OnBottomReachedListener? = null

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        viewModel.getMealAt(position)?.let { meal ->
            holder.apply {
                Timber.d("!: " + meal.calories.toString())
                Timber.d("!: " + meal.id.toString())
                textView1.text = meal.calories.toString()
                textView2.text = meal.id.toString()
            }
        }

//        if (position == itemCount - 1) {
//            onBottomReachedListener?.onBottomReached(position)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_calories_item, parent, false))
    }

    override fun getItemCount(): Int {
        return viewModel.getMealListSize()
    }

//    fun setOnBottomReachedListener(onBottomReachedListener: OnBottomReachedListener) {
//        this.onBottomReachedListener = onBottomReachedListener
//    }

//    interface OnBottomReachedListener {
//        fun onBottomReached(position: Int)
//    }

    class MealsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView1 by lazy { view.findViewById<TextView>(R.id.tvTitle1) as TextView }
        val textView2 by lazy { view.findViewById<TextView>(R.id.tvTitle2) as TextView }
    }

}