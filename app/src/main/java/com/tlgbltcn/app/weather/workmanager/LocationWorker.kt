package com.tlgbltcn.app.weather.workmanager

import android.content.Context
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class LocationWorker(context : Context, param : WorkerParameters) : Worker(context, param) {
    override fun doWork(): Result {
        val workManager : WorkManager = WorkManager.getInstance()



        return Result.SUCCESS
    }
}