package com.tlgbltcn.app.weather.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tlgbltcn.app.weather.model.fivedays.ListItem
import com.tlgbltcn.app.weather.model.today.WeatherItem


class ListItemConverter {

    @TypeConverter
    fun fromString(value: String): List<ListItem> {
        val listType = object : TypeToken<List<ListItem>>() {}.type
        return Gson().fromJson<List<ListItem>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ListItem>): String {
        return Gson().toJson(list)
    }
}