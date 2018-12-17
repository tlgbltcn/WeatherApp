package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.fifteendays.Temp
import com.tlgbltcn.app.weather.model.fivedays.City
import com.tlgbltcn.app.weather.model.today.Clouds


class TempConverter {

    @TypeConverter
    fun fromString(value: String): Temp {
        val listType = object : TypeToken<Temp>() {}.type
        return Gson().fromJson<Temp>(value, listType)
    }

    @TypeConverter
    fun fromList(temp: Temp): String {
        return Gson().toJson(temp)
    }
}