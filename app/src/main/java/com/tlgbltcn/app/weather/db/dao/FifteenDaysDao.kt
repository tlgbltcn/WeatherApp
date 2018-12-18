package com.tlgbltcn.app.weather.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tlgbltcn.app.weather.db.entities.FifteenDaysEntity

@Dao
abstract class FifteenDaysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(fifteenDaysEntity: FifteenDaysEntity)
}