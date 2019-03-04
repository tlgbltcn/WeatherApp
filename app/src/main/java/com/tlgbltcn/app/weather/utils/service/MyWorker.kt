package com.tlgbltcn.app.weather.utils.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by tolga bolatcan on 13.01.2019
 */
class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}