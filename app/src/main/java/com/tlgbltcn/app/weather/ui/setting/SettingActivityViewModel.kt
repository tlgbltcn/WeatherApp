package com.tlgbltcn.app.weather.ui.setting

import android.app.Application
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.core.BaseViewModel

class SettingActivityViewModel(app : Application) : BaseViewModel(app) {

    init {
        (app as App).component.inject(this)
    }
}