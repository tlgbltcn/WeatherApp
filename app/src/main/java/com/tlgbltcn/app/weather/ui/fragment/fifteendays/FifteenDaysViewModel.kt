package com.tlgbltcn.app.weather.ui.fragment.fifteendays

import android.app.Application
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.BaseViewModel

class FifteenDaysViewModel(app : Application) : BaseViewModel(app) {


    init {
        (getApplication<Application>() as App).component.inject(this)
    }
}