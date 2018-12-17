package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.fivedays.City


class CityConverter {

    @TypeConverter
    fun fromString(value: String): City {
        val listType = object : TypeToken<City>() {}.type
        return Gson().fromJson<City>(value, listType)
    }

    @TypeConverter
    fun fromList(city: City): String {
        return Gson().toJson(city)
    }
}