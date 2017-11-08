package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.kotlinblog.dontgetfat.data.database.entity.DayWithMeals;

/**
 * TODO: Add a class header comment!
 */
@Dao
public interface DayWithMealsDao {
    @Query("SELECT * FROM days ORDER BY id DESC LIMIT 1")
    DayWithMeals getLastDayWithAllMeals();

    @Query("SELECT * FROM days ORDER BY id DESC LIMIT 1")
    LiveData<DayWithMeals> getLastDayWithMealsLiveData();
}
