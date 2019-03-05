package com.tlgbltcn.app.weather.di.component

import android.content.Context
import android.content.SharedPreferences
import com.tlgbltcn.app.weather.App
import com.tlgbltcn.app.weather.di.module.ApplicationModule
import com.tlgbltcn.app.weather.di.module.DatabaseModule
import com.tlgbltcn.app.weather.di.module.NetModule
import com.tlgbltcn.app.weather.di.module.RepoModule
import com.tlgbltcn.app.weather.repository.TodayRepository
import com.tlgbltcn.app.weather.ui.fragment.fifteendays.FifteenDaysFragment
import com.tlgbltcn.app.weather.ui.fragment.fifteendays.FifteenDaysViewModel
import com.tlgbltcn.app.weather.ui.fragment.fivedays.FiveDaysFragment
import com.tlgbltcn.app.weather.ui.fragment.fivedays.FiveDaysViewModel
import com.tlgbltcn.app.weather.ui.fragment.today.TodayFragment
import com.tlgbltcn.app.weather.ui.fragment.today.TodayFragmentViewModel
import com.tlgbltcn.app.weather.ui.main.MainActivity
import com.tlgbltcn.app.weather.ui.main.MainActivityViewModel
import com.tlgbltcn.app.weather.ui.setting.SettingActivity
import com.tlgbltcn.app.weather.ui.setting.SettingActivityViewModel
import com.tlgbltcn.app.weather.ui.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton


@Singleton

@Component(modules = arrayOf(ApplicationModule::class,NetModule::class,DatabaseModule::class,RepoModule::class))


interface ApplicationComponent {
    fun app(): App

    fun context(): Context

    fun preferences(): SharedPreferences

    fun todayRepo() : TodayRepository

    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(mainActivity: MainActivity)

    fun inject(todayFragmentViewModel: TodayFragmentViewModel)

    fun inject(todayFragment: TodayFragment)

    fun inject(fifteenDaysFragment: FifteenDaysFragment)

    fun inject(fifteenDaysViewModel: FifteenDaysViewModel)

    fun inject(fiveDaysFragment: FiveDaysFragment)

    fun inject(fiveDaysViewModel: FiveDaysViewModel)

    fun inject(settingActivity: SettingActivity)

    fun inject(settingActivityViewModel: SettingActivityViewModel)

    fun inject(splashActivity: SplashActivity) {}
}
