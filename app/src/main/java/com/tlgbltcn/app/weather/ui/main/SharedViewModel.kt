package com.tlgbltcn.app.weather.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tlgbltcn.app.weather.core.BaseViewModel

class SharedViewModel(app: Application) : BaseViewModel(app) {


    var lat: MutableLiveData<Double> = MutableLiveData()

    var lon: MutableLiveData<Double> = MutableLiveData()

    var locationPair : MutableLiveData<Double> = MutableLiveData()


    fun setMyLocationPair(loc : Double?) {
        locationPair.postValue(loc)
    }

    fun getMyLocationPair() : MutableLiveData<Double> = locationPair

}
