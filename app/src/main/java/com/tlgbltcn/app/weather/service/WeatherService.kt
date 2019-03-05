package com.tlgbltcn.app.weather.service

import androidx.lifecycle.LiveData
import com.tlgbltcn.app.weather.db.entities.TodayEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getTodaybyCity(@Query("q") city : String, @Query("appid") apiKey : String) : LiveData<ApiResponse<TodayEntity>>

    @GET("/data/2.5/weather")
    fun getTodaybyCoor(@Query("lat") lat : Double,
                       @Query("lon") lon : Double,
                       @Query("appid")  apiKey: String) : LiveData<ApiResponse<TodayEntity>>


}