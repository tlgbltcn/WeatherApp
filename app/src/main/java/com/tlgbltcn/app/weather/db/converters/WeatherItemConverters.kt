package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.WeatherItem

class WeatherItemConverters {

    @TypeConverter
    fun fromListToString(_list : List<WeatherItem>) : String? {
        var gson = Gson()
        val type = object : TypeToken<List<WeatherItem>>(){}.type
        var json = gson.toJson(_list,type)
        return json
    }

    @TypeConverter
    fun fromStringToList(_string: String) : List<WeatherItem>{
        val type = object : TypeToken<List<WeatherItem>>(){}.type
        return Gson().fromJson(_string, type)
    }
}