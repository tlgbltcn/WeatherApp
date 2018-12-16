package com.tlgbltcn.app.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.AppConstant
import com.tlgbltcn.app.weather.AppExecutors
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.db.entities.TodayEntity
import com.tlgbltcn.app.weather.model.today.Today
import com.tlgbltcn.app.weather.service.WeatherService
import javax.inject.Inject

class TodayRepositoryImp @Inject constructor(var db: AppDatabase,
                                             var api: WeatherService,
                                             var appExecutors: AppExecutors) : TodayRepository {

    override fun getCityName(): LiveData<String> {
        return  db.todayDao().getCityName()
    }

    val data: MediatorLiveData<Today> = MediatorLiveData()

    override fun loadToday(latitude: Double, longitude: Double): LiveData<Resource<TodayEntity>> {

        return object : NetworkBoundResource<TodayEntity, Today>(appExecutors) {
            override fun saveCallResult(item: Today) {
                val todayResult = TodayEntity(
                        id = 1,
                        main = item.weather?.get(0)?.main,
                        mainDesc = item.weather?.get(0)?.description,
                        icon = item.weather?.get(0)?.icon,
                        temp = item.main?.temp,
                        wind_speed = item.wind?.speed,
                        wind_deg = item.wind?.deg,
                        humidity = item.main?.humidity,
                        temp_max = item.main?.tempMax,
                        temp_min = item.main?.tempMin,
                        pressure = item.main?.pressure,
                        clouds_all = item.clouds?.all,
                        cityCountry = item.sys?.country,
                        sunrise = item.sys?.sunrise,
                        sunset = item.sys?.sunset,
                        cityId = item.id,
                        cityLat = item.coord?.lat,
                        cityLon = item.coord?.lon,
                        cityName = "${item.name}",
                        dt = item.dt
                )
                db.todayDao().insert(todayResult)
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