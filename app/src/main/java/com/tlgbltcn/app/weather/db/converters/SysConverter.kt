package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.Sys


class SysConverter {

    @TypeConverter
    fun fromString(value: String): Sys {
        val listType = object : TypeToken<Sys>() {}.type
        return Gson().fromJson<Sys>(value, listType)
    }

    @TypeConverter
    fun fromList(sys: Sys): String {
        return Gson().toJson(sys)
    }
}