package com.tlgbltcn.app.weather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.model.today.Today

@Dao
abstract class TodayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(today : Today)


    @Query("SELECT * FROM Today WHERE myId = :id")
    abstract fun getToday(id : Int) : LiveData<Today>

}

