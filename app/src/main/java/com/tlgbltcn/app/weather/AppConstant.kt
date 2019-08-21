package com.tlgbltcn.app.weather

object AppConstant {

    const val BASE_URL = "https://api.openweathermap.org"
    const val API_KEY = "29126df019f086747b1870a31b2e8bc3"
    const val WEATHER_ICON_URL = "http://openweathermap.org/img/w/"

    const val REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key"
    const val LOCATION_KEY = "location-key"
    const val LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key"
    const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    const val REQUEST_CHECK_SETTINGS = 10

    const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2
}