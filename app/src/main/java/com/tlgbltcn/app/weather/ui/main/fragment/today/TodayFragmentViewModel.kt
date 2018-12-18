package com.tlgbltcn.app.weather.ui.main.fragment.today

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.github.vo.Resource
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.base.BaseViewModel
import com.tlgbltcn.app.weather.model.today.Coord
import com.tlgbltcn.app.weather.db.entities.TodayEntity
import com.tlgbltcn.app.weather.repository.TodayRepository
import javax.inject.Inject

class TodayFragmentViewModel(app: Application) : BaseViewModel(app) {

    @Inject
    lateinit var repo: TodayRepository

    init {
        (getApplication<Application>() as App).component.inject(this)
    }

    var _coords: MutableLiveData<Coord> = MutableLiveData()

    val coords: LiveData<Coord>
        get() = _coords

    fun setCoords(lat : Double? , lon : Double?) {
        val model = Coord(lat,lon)
        if(_coords.value == model) return
        else _coords.value = model
    }

    fun getTodayByCoord(lat : Double, lon : Double) : LiveData<Resource<TodayEntity>>{
        return repo.loadToday(latitude = lat, longitude = lon)
    }

}
