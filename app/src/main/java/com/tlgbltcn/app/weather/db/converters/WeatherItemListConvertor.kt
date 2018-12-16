package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.WeatherItem


class WeatherItemListConvertor {

    @TypeConverter
    fun fromString(value: String): List<WeatherItem> {
        val listType = object : TypeToken<List<WeatherItem>>() {}.type
        return Gson().fromJson<List<WeatherItem>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<WeatherItem>): String {
        return Gson().toJson(list)
    }
}