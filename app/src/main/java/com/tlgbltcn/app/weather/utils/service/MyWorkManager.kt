package com.tlgbltcn.app.weather.utils.service

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.work.*
import com.google.common.util.concurrent.ListenableFuture
import java.util.*

/**
 * Created by tolga bolatcan on 13.01.2019
 */
@SuppressLint("RestrictedApi")
class MyWorkManager : WorkManager() {
    override fun enqueue(requests: MutableList<out WorkRequest>): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelAllWorkByTag(tag: String): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beginWith(work: MutableList<OneTimeWorkRequest>): WorkContinuation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enqueueUniqueWork(uniqueWorkName: String, existingWorkPolicy: ExistingWorkPolicy, work: MutableList<OneTimeWorkRequest>): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfoById(id: UUID): ListenableFuture<WorkInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastCancelAllTimeMillisLiveData(): LiveData<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelAllWork(): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfosForUniqueWorkLiveData(uniqueWorkName: String): LiveData<MutableList<WorkInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pruneWork(): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beginUniqueWork(uniqueWorkName: String, existingWorkPolicy: ExistingWorkPolicy, work: MutableList<OneTimeWorkRequest>): WorkContinuation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelUniqueWork(uniqueWorkName: String): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun enqueueUniquePeriodicWork(uniqueWorkName: String, existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy, periodicWork: PeriodicWorkRequest): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastCancelAllTimeMillis(): ListenableFuture<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfoByIdLiveData(id: UUID): LiveData<WorkInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfosByTag(tag: String): ListenableFuture<MutableList<WorkInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfosForUniqueWork(uniqueWorkName: String): ListenableFuture<MutableList<WorkInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelWorkById(id: UUID): Operation {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorkInfosByTagLiveData(tag: String): LiveData<MutableList<WorkInfo>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}