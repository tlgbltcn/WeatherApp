package com.tlgbltcn.app.weather.ui.main

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.databinding.ObservableField
import com.tlgbltcn.app.weather.R
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.base.BaseViewModel
import com.tlgbltcn.app.weather.repository.TodayRepositoryImp
import javax.inject.Inject

class MainActivityViewModel(app: Application) : BaseViewModel(app) {


    private var sharedPreferences : SharedPreferences

    var toolbarTitle : ObservableField<String> = ObservableField("Weather App")

    @Inject
    lateinit var repo : TodayRepositoryImp

    init {
        (app as? App)?.component?.inject(this)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
    }

    fun getAppName() = getApplication<Application>().resources.getString(R.string.app_name)

    fun initSharedPreferences() {

        if(sharedPreferences.all.isEmpty()){
            with(sharedPreferences.edit()){
                putString(getApplication<Application>().getString(R.string.temperature).toLowerCase(),
                        getApplication<Application>().getString(R.string.celsius)).apply()
                putString(getApplication<Application>().getString(R.string.wind).toLowerCase(),
                        getApplication<Application>().getString(R.string.kmh)).apply()
                putString(getApplication<Application>().getString(R.string.pressure).toLowerCase(),
                        getApplication<Application>().getString(R.string.mmHg)).apply()
                putString(getApplication<Application>().getString(R.string.precipitation).toLowerCase(),
                        getApplication<Application>().getString(R.string.in_)).apply()
                putString(getApplication<Application>().getString(R.string.day_format).toLowerCase(),
                        getApplication<Application>().getString(R.string.pm)).apply()
            }
        }
    }




}