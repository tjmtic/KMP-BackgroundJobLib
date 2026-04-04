package com.abyxcz.viewpoint.background

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AndroidBackgroundManager(private val context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun <T : androidx.work.ListenableWorker> schedulePeriodicWork(
        workName: String,
        workerClass: Class<T>,
        repeatIntervalInMinutes: Long = 60,
    ) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = androidx.work.PeriodicWorkRequest.Builder(
            workerClass,
            repeatIntervalInMinutes, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            workName,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    fun cancelWork(workName: String) {
        workManager.cancelUniqueWork(workName)
    }
}
