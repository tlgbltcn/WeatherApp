package com.tlgbltcn.app.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tlgbltcn.app.weather.db.converters.*
import com.tlgbltcn.app.weather.db.dao.TodayDao
import com.tlgbltcn.app.weather.db.entities.FifteenDaysEntity
import com.tlgbltcn.app.weather.db.entities.FiveDaysEntity
import com.tlgbltcn.app.weather.db.entities.TodayEntity

@Database(entities = [TodayEntity::class, FiveDaysEntity::class, FifteenDaysEntity::class],
        version = 1)
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
    abstract fun todayDao(): TodayDao
}


