package com.tlgbltcn.app.weather.ui.setting.fragment

import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import com.tlgbltcn.app.weather.R


class SettingsFragment : PreferenceFragment() {

    private val TAG = SettingsFragment::class.java.name
    val SETTINGS_SHARED_PREFERENCES_FILE_NAME = "$TAG.SETTINGS_SHARED_PREFERENCES_FILE_NAME"


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val hostActivity = activity
        val prefs = PreferenceManager.getDefaultSharedPreferences(hostActivity)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.preferences)
        var temperatureSummary : String? = null
        val temperature = findPreference("temperature") as ListPreference
        with(temperature){
            if (value != getString(R.string.celsius) && value != getString(R.string.fahrenheit)){
                value = getString(R.string.celsius)
            }
        }
        when(temperature.value){
            getString(R.string.celsius) -> temperatureSummary = getString(R.string.celsius_des)
            getString(R.string.fahrenheit) -> temperatureSummary = getString(R.string.fahrenheit_des)
        }
        temperature.summary = temperatureSummary
        temperature.setDefaultValue(getString(R.string.celsius_des))
        temperature.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, p1 ->
            when(p1){
                "°C" -> {temperatureSummary = getString(R.string.celsius_des)}
                "°F" -> {temperatureSummary = getString(R.string.fahrenheit_des)}
            }

            temperature.summary = temperatureSummary


            true
        }

        var windSummary : String? = null
        val wind = findPreference("wind") as ListPreference
        with(wind){
            if(value != getString(R.string.knots) && value != getString(R.string.kmh) && value != getString(R.string.mph) && value != getString(R.string.ms)){
                value = getString(R.string.kmh)
            }
        }

        when(wind.value){
            getString(R.string.kmh) -> {temperatureSummary = getString(R.string.kmh)}
            getString(R.string.knots) -> {temperatureSummary = getString(R.string.knots)}
            getString(R.string.mph) -> {temperatureSummary = getString(R.string.mph)}
            getString(R.string.ms) -> {temperatureSummary = getString(R.string.ms)}
        }
        wind.summary = temperatureSummary
        wind.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, p1 ->
            when(p1){
                "knots" -> {windSummary = getString(R.string.knots)}
                "kilometer" -> {windSummary = getString(R.string.kmh)}
                "meter" -> {windSummary = getString(R.string.ms)}
                "mile" -> {windSummary = getString(R.string.mph)}
            }

            wind.summary = windSummary

            true
        }


        var precipitationSummary : String? = null
        val precipitation = findPreference("precipitation") as ListPreference
        with(precipitation){
            if(value != getString(R.string.in_) && value != getString(R.string.mm)){
                value = getString(R.string.in_)
            }
        }

        when(precipitation.value){
            getString(R.string.in_) -> { precipitationSummary = getString(R.string.in_)}
            getString(R.string.mm) -> { precipitationSummary = getString(R.string.mm)}
        }
        precipitation.summary = precipitationSummary
        precipitation.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, p1 ->
            when(p1){
                "in" -> {precipitationSummary = getString(R.string.in_)}
                "mm" -> {precipitationSummary = getString(R.string.mm)}
            }

            precipitation.summary = precipitationSummary

            true
        }


        var pressureSummary : String? = null
        val pressure = findPreference("pressure") as ListPreference
        with(pressure){
            if(value != getString(R.string.inHg) && value != getString(R.string.mmHg) && value != getString(R.string.mBar)){
                value = getString(R.string.mmHg)
            }
        }

        when(pressure.value){
            getString(R.string.mmHg) -> {pressureSummary = getString(R.string.mmHg)}
            getString(R.string.inHg) -> {pressureSummary = getString(R.string.inHg)}
            getString(R.string.mBar) -> {pressureSummary = getString(R.string.mBar)}
        }
        pressure.summary = pressureSummary
        pressure.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, p1 ->
            when(p1){
                "inHg" -> {pressureSummary = getString(R.string.inHg)}
                "mmHg" -> {pressureSummary = getString(R.string.mmHg)}
                "mBar" -> {pressureSummary = getString(R.string.mBar)}
            }

            pressure.summary = pressureSummary

            true
        }


        var dayFormatSummary : String? = null
        val dayFormat = findPreference("day_format") as ListPreference

        with(dayFormat){
            if(value != getString(R.string.pm) && value != getString(R.string.am)){
                value = getString(R.string.pm)
            }
        }

        when(dayFormat.value){
            getString(R.string.pm) -> { dayFormatSummary = getString(R.string.pm)}
            getString(R.string.am) -> { dayFormatSummary = getString(R.string.am)}

        }
        dayFormat.summary = dayFormatSummary
        dayFormat.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, p1 ->
            when(p1){
                "12h" -> {dayFormatSummary = getString(R.string.am)}
                "24h" -> {dayFormatSummary = getString(R.string.pm)}
            }

            dayFormat.summary = dayFormatSummary

            true
        }

    }



}