package com.tlgbltcn.app.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.AppConstant
import com.tlgbltcn.app.weather.AppExecutors
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.model.today.Today
import com.tlgbltcn.app.weather.service.WeatherService
import javax.inject.Inject

class TodayRepositoryImp @Inject constructor(var db: AppDatabase,
                                             var api: WeatherService,
                                             var appExecutors: AppExecutors) : TodayRepository {


    val data: MediatorLiveData<Today> = MediatorLiveData()

    override fun loadToday(latitude: Double, longitude: Double): LiveData<Resource<Today>> {

        return object : NetworkBoundResource<Today, Today>(appExecutors) {
            override fun saveCallResult(item: Today) {

                db.todayDao().insert(item)
            }

            override fun shouldFetch(data: Today?) = data == null

            override fun loadFromDb() = db.todayDao().getToday(1)

            override fun createCall() = api.getTodaybyCoor(
                    lat = latitude,
                    lon = longitude,
                    apiKey = AppConstant.API_KEY)
        }.asLiveData()
    }


}