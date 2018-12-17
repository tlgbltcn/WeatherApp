package com.tlgbltcn.app.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tlgbltcn.app.weather.db.converters.*
import com.tlgbltcn.app.weather.db.dao.ExampleDao
import com.tlgbltcn.app.weather.db.dao.TodayDao
import com.tlgbltcn.app.weather.db.entities.Example
import com.tlgbltcn.app.weather.model.fifteendays.FifteenDaysEntity
import com.tlgbltcn.app.weather.model.fivedays.FiveDaysEntity
import com.tlgbltcn.app.weather.model.today.Today

@Database(entities = [Example::class,Today::class,FiveDaysEntity::class,FifteenDaysEntity::class],
        version = 2)
@TypeConverters(CloudsConverter::class,
        CoordConverter::class,
        MainConverter::class,
        SysConverter::class,
        WeatherItemListConverter::class,
        WindConverter::class,
        CityConverter::class,
        ListItemConverter::class,
        RainConverter::class,
        TempConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    abstract fun todayDao(): TodayDao
}


