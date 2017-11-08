package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.kotlinblog.dontgetfat.data.database.entity.Meal;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
@Dao
public interface MealsDao {
    @Insert
    long insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Update
    void updateMeal(Meal meal);

    @Query("SELECT * FROM meals ORDER BY id DESC LIMIT 1;")
    @Nullable
    Meal getLastMeal();

    @Query("SELECT * FROM meals WHERE day_id=:dayId;")
    LiveData<List<Meal>> getAllMealsForGivenDay(Long dayId);
}
