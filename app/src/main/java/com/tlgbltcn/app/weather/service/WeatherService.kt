package com.tlgbltcn.app.weather.service

import androidx.lifecycle.LiveData
import com.tlgbltcn.app.weather.model.today.Today
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    //  "https://samples.openweathermap.org/"


    @GET("/data/2.5/weather")
    fun getTodaybyCity(@Query("q") city : String, @Query("appid") apiKey : String) : LiveData<ApiResponse<Today>>

    @GET("/data/2.5/weather")
    fun getTodaybyCoor(@Query("lat") lat : Double, @Query("lon") lon : Double, @Query("appid")  apiKey: String) : LiveData<ApiResponse<Today>>

}