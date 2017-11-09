package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.kotlinblog.dontgetfat.data.database.entity.DayWithMeals;

import java.util.Date;

/**
 * Dao returning DayWithMeals object
 */
@Dao
public interface DayWithMealsDao {
    @Query("SELECT * FROM days WHERE date BETWEEN :start and :end")
    LiveData<DayWithMeals> getCurrentDayWithMeals(Date start, Date end);
}
