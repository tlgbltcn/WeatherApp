package com.tlgbltcn.app.weather.repository

import androidx.lifecycle.LiveData
import com.tlgbltcn.app.weather.db.entities.TodayEntity
import com.tlgbltcn.app.weather.service.Resource

interface TodayRepository {

    fun loadToday(latitude : Double, longitude : Double) : LiveData<Resource<TodayEntity>>
}