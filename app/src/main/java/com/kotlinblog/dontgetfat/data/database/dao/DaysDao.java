package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kotlinblog.dontgetfat.data.model.Day;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
@Dao
public interface DaysDao {
    @Insert
    long insertDay(Day day);

    @Delete
    void deleteDay(Day day);

    @Query("SELECT * FROM days ORDER BY date ASC")
    List<Day> getAllDays();

    @Query("SELECT * FROM days WHERE id = :id")
    Day getDayById(long id);
}
