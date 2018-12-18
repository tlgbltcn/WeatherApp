package com.tlgbltcn.app.weather.repository

import androidx.lifecycle.LiveData
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.db.entities.TodayEntity

interface TodayRepository {

    fun loadToday(latitude : Double, longitude : Double) : LiveData<Resource<TodayEntity>>
}