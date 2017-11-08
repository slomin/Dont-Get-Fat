package com.kotlinblog.dontgetfat.view.calories

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import com.kotlinblog.dontgetfat.R
import com.kotlinblog.dontgetfat.data.database.entity.Meal

class CaloriesAdapter(private var viewModel: CaloriesViewModel,
                      private val context: Context,
                      private val caloriesFragmentOnClickListener: CaloriesAdapterOnClickListener)
    : RecyclerView.Adapter<CaloriesAdapter.MealsViewHolder>() {

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        viewModel.getMealAt(position)?.let { meal ->
            holder.apply {
                mealNumber.text = context.getString(R.string.meal_number, (position + 1))
                mealTime.text = viewModel.getMealTime(meal.date)
                mealCalories.text = context.getString(R.string.calories, meal.calories)
                menuButton.setOnClickListener {
                    val popupMenu = PopupMenu(context, holder.menuButton)
                    popupMenu.inflate(R.menu.fragment_calories_popup_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.item_edit -> caloriesFragmentOnClickListener.editMealPicker(meal)
                            R.id.item_delete -> viewModel.deleteMeal(meal)
                        }
                        true
                    }
                    popupMenu.show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        return MealsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_calories_item, parent, false))
    }

    override fun getItemCount(): Int {
        return viewModel.getMealListSize()
    }

    class MealsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mealNumber by lazy { view.findViewById<TextView>(R.id.tvMealNumber) as TextView }
        val mealTime by lazy { view.findViewById<TextView>(R.id.tvMealTime) as TextView }
        val mealCalories by lazy { view.findViewById<TextView>(R.id.tvMealCalories) as TextView }
        val menuButton by lazy { view.findViewById<ImageButton>(R.id.btnItemMenu) as ImageButton }
    }

    interface CaloriesAdapterOnClickListener {
        fun editMealPicker(meal: Meal)
    }

}