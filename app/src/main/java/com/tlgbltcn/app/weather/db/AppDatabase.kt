package com.tlgbltcn.app.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tlgbltcn.app.weather.db.converters.*
import com.tlgbltcn.app.weather.db.dao.ExampleDao
import com.tlgbltcn.app.weather.db.dao.TodayDao
import com.tlgbltcn.app.weather.db.entities.Example
import com.tlgbltcn.app.weather.model.today.Today

@Database(entities = arrayOf(Example::class, Today::class), version = 2)
@TypeConverters(CloudsConvertor::class,
        CoordConvertor::class,
        MainConvertor::class,
        SysConvertor::class,
        WeatherItemConverters::class,
        WindConvertor::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun todayDao(): TodayDao
}


/**
 *



 */