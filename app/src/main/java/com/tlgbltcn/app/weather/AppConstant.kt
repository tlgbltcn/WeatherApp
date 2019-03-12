package com.tlgbltcn.app.weather

object AppConstant {

    val BASE_URL = "https://api.openweathermap.org"
    val API_KEY = "29126df019f086747b1870a31b2e8bc3"
    val WEATHER_ICON_URL = "http://openweathermap.org/img/w/"

    val REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key"
    val LOCATION_KEY = "location-key"
    val LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key"
    val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    val REQUEST_CHECK_SETTINGS = 10

    val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2
}