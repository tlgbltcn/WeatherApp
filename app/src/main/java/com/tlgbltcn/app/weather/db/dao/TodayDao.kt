package com.tlgbltcn.app.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tlgbltcn.app.weather.db.entities.TodayEntity

@Dao
abstract class TodayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(todayEntity : TodayEntity)

    @Query("SELECT * FROM TodayEntity WHERE myId = :id")
    abstract fun getToday(id : Int) : LiveData<TodayEntity>

}

