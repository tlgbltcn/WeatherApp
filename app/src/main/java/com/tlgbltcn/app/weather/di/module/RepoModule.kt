package com.tlgbltcn.app.weather.di.module

import com.tlgbltcn.app.weather.AppExecutors
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.db.dao.TodayDao
import com.tlgbltcn.app.weather.repository.TodayRepository
import com.tlgbltcn.app.weather.repository.TodayRepositoryImp
import com.tlgbltcn.app.weather.service.WeatherService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideTodayRepo(db: AppDatabase, api : WeatherService, appExecutors: AppExecutors) : TodayRepository {

        return TodayRepositoryImp(db,api,appExecutors)
    }
}