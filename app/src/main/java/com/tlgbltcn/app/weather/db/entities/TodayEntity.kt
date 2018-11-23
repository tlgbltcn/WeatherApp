package com.tlgbltcn.app.weather.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodayEntity")
data class TodayEntity(

        @PrimaryKey
        var id : Long? = null,

        var cityId : Int? = null,

        var cityName : String? = null,

        var cityLat : Double? = null,

        var cityLon : Double? = null,

        var cityCountry : String? = null,

        var dt : Long? = null,

        var main : String? = null,

        var mainDesc : String? = null,

        var icon : String? = null,

        var temp : Double? = null,

        var pressure : Double? = null,

        var humidity : Int? = null,

        var temp_min : Double? = null,

        var temp_max : Double? = null,

        var wind_speed : Double? = null,

        var wind_deg : Double? = null,

        var clouds_all : Int? = null,

        var rain_3h : Double? = null,

        var country : String? = null,

        var sunrise : Int? = null,

        var sunset : Int? = null
)