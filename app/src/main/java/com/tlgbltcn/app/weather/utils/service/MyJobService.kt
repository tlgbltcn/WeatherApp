package com.tlgbltcn.app.weather.utils.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

/**
 * Created by tolga bolatcan on 12.01.2019
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService : JobService() {
    override fun onStopJob(params: JobParameters?): Boolean {
        jobFinished(params, false)
        Log.i("JobSchedular", "JobSchedular is stop $params =====")
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        jobFinished(params, false)
        Log.i("JobSchedular", "JobSchedular is start $params *******")
        return false
    }
}