package com.tlgbltcn.app.weather.ui.main.fragment.fivedays

import android.app.Application
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.base.BaseViewModel

class FiveDaysViewModel(app : Application) : BaseViewModel(app) {

    init {
        (getApplication<App>()).component.inject(this)
    }
}