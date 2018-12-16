package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.Clouds


class CloudsConvertor {

    @TypeConverter
    fun fromString(value: String): Clouds {
        val listType = object : TypeToken<Clouds>() {}.type
        return Gson().fromJson<Clouds>(value, listType)
    }

    @TypeConverter
    fun fromList(clouds: Clouds): String {
        return Gson().toJson(clouds)
    }
}