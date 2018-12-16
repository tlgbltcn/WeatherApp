package com.tlgbltcn.app.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.db.entities.TodayEntity

@Dao
abstract class TodayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(today : TodayEntity)

    @Query("SELECT * FROM TodayEntity WHERE cityLat = :lat AND cityLon = :lon")
    abstract fun getTodayByCoord(lat : Double, lon : Double) : LiveData<TodayEntity>

    @Query("SELECT * FROM TodayEntity WHERE id = :id")
    abstract fun getToday(id : Long) : LiveData<TodayEntity>

    @Query("SELECT cityName FROM TodayEntity LIMIT 1")
    abstract fun getCityName() : LiveData<String>
}

