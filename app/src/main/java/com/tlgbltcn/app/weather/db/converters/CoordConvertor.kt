package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.tlgbltcn.app.weather.model.today.Coord
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CoordConvertor {

    @TypeConverter
    fun fromString(value: String): Coord {
        val listType = object : TypeToken<Coord>() {}.type
        return Gson().fromJson<Coord>(value, listType)
    }

    @TypeConverter
    fun fromList(coord: Coord): String {
        return Gson().toJson(coord)
    }
}