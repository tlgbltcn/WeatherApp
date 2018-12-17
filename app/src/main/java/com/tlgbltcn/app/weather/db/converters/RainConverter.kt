package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.fivedays.Rain
import com.tlgbltcn.app.weather.model.today.Clouds


class RainConverter {

    @TypeConverter
    fun fromString(value: String): Rain {
        val listType = object : TypeToken<Rain>() {}.type
        return Gson().fromJson<Rain>(value, listType)
    }

    @TypeConverter
    fun fromList(rain: Rain): String {
        return Gson().toJson(rain)
    }
}