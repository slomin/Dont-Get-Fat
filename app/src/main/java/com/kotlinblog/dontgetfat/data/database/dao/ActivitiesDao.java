package com.kotlinblog.dontgetfat.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.kotlinblog.dontgetfat.data.model.Exercise;

/**
 * TODO: Add a class header comment!
 */
@Dao
public interface ActivitiesDao {
    @Insert
    long insertActivity(Exercise exercise);
}
