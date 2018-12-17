package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.today.Main


class MainConverter {

    @TypeConverter
    fun fromString(value: String): Main {
        val listType = object : TypeToken<Main>() {}.type
        return Gson().fromJson<Main>(value, listType)
    }

    @TypeConverter
    fun fromList(main: Main): String {
        return Gson().toJson(main)
    }
}