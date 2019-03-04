package com.tlgbltcn.app.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.tlgbltcn.app.weather.AppConstant
import com.tlgbltcn.app.weather.AppExecutors
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.db.entities.TodayEntity
import com.tlgbltcn.app.weather.service.Resource
import com.tlgbltcn.app.weather.service.WeatherService
import javax.inject.Inject

class TodayRepositoryImp @Inject constructor(var db: AppDatabase,
                                             var api: WeatherService,
                                             var appExecutors: AppExecutors) : TodayRepository {


    val data: MediatorLiveData<TodayEntity> = MediatorLiveData()

    override fun loadToday(latitude: Double, longitude: Double): LiveData<Resource<TodayEntity>> {

        return object : NetworkBoundResource<TodayEntity, TodayEntity>(appExecutors) {
            override fun saveCallResult(item: TodayEntity) {

                db.todayDao().insert(item)
            }

            override fun shouldFetch(data: TodayEntity?) = data == null

            override fun loadFromDb() = db.todayDao().getToday(1)

            override fun createCall() = api.getTodaybyCoor(
                    lat = latitude,
                    lon = longitude,
                    apiKey = AppConstant.API_KEY)
        }.asLiveData()
    }


}