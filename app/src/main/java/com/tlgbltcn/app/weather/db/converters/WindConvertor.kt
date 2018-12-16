package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.Wind


class WindConvertor {

    @TypeConverter
    fun fromString(value: String): Wind {
        val listType = object : TypeToken<Wind>() {}.type
        return Gson().fromJson<Wind>(value, listType)
    }

    @TypeConverter
    fun fromList(wind: Wind): String {
        return Gson().toJson(wind)
    }
}