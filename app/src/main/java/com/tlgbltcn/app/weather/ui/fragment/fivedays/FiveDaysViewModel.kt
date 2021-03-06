package com.tlgbltcn.app.weather.ui.fragment.fivedays

import android.app.Application
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.BaseViewModel

class FiveDaysViewModel(app : Application) : BaseViewModel(app) {

    init {
        (getApplication<App>()).component.inject(this)
    }
}