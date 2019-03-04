package com.tlgbltcn.app.weather.utils.service

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Created by tolga bolatcan on 13.01.2019
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJob : JobScheduler() {
    override fun enqueue(job: JobInfo, work: JobWorkItem): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun schedule(job: JobInfo): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllPendingJobs(): MutableList<JobInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPendingJob(jobId: Int): JobInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel(jobId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}