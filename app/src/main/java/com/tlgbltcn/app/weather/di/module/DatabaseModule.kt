package com.tlgbltcn.app.weather.di.module

import androidx.room.Room
import android.content.Context
import com.tlgbltcn.app.weather.db.AppDatabase
import com.tlgbltcn.app.weather.repository.TodayRepository
import com.tlgbltcn.app.weather.repository.TodayRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, "example-db").build()
    }

}