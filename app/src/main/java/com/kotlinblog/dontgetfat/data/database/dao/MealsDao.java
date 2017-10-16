package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kotlinblog.dontgetfat.data.model.Meal;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
@Dao
public interface MealsDao {
    @Insert
    long insertMeal(Meal meal);

    @Query("SELECT * FROM meals ORDER BY id DESC LIMIT 1;")
    @Nullable
    Meal getLastMeal();

    @Query("SELECT * FROM meals WHERE day_id=:dayId;")
    @Nullable
    List<Meal> getAllMealsForGivenDay(Long dayId);
}
