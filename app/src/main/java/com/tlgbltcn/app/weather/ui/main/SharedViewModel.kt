package com.tlgbltcn.app.weather.ui.main

import android.app.Application
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.*
import com.tlgbltcn.app.weather.core.base.BaseViewModel

class SharedViewModel(app: Application) : BaseViewModel(app) {


    var lat: MutableLiveData<Double> = MutableLiveData()

    var lon: MutableLiveData<Double> = MutableLiveData()

    val validate: MediatorLiveData<Boolean> = MediatorLiveData()


    var locationData: MediatorLiveData<Pair<Double, Double>> = MediatorLiveData()


    fun setLocation() {

    }

    fun updateLocation() {
        val mLon = 0.0
        val mLat = 0.0

        //locationData.addSource(lat) { t -> locationData.value = Pair(t!!, mLon) }
        //locationData.addSource(lon) { t -> locationData.value = Pair(mLat, t!!) }
    }

    data class LocationLive(
            val mLat: Double,
            val mLon: Double)
}
