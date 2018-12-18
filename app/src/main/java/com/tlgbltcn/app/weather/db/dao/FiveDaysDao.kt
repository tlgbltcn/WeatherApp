package com.tlgbltcn.app.weather.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.tlgbltcn.app.weather.db.entities.FiveDaysEntity

@Dao
abstract class FiveDaysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(fiveDaysEntity: FiveDaysEntity)
}