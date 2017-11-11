package com.kotlinblog.dontgetfat.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.kotlinblog.dontgetfat.data.database.DgfDatabase
import org.junit.After
import org.junit.Before
import java.io.IOException


abstract class AbstractDbTest {
    protected lateinit var dgfDatabase: DgfDatabase

    @Before
    fun initDb() {
        dgfDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                DgfDatabase::class.java)
                .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        dgfDatabase.close()
    }
}