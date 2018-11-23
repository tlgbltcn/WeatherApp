package com.tlgbltcn.app.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tlgbltcn.app.weather.db.dao.ExampleDao
import com.tlgbltcn.app.weather.db.dao.TodayDao
import com.tlgbltcn.app.weather.db.entities.Example
import com.tlgbltcn.app.weather.db.entities.TodayEntity

@Database(entities = arrayOf(Example::class, TodayEntity::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun todayDao(): TodayDao
}