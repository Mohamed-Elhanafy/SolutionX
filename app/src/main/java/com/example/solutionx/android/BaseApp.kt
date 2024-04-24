package com.example.solutionx.android

import am.leon.utilities.android.helpers.logging.LoggerFactory
import am.leon.utilities.android.helpers.logging.writers.DummyWriter
import am.leon.utilities.android.helpers.logging.writers.FileWriter
import am.leon.utilities.android.helpers.logging.writers.LogcatWriter
import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import com.example.solutionx.BuildConfig
import com.example.solutionx.android.helpers.logger.LoggerProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import androidx.work.Configuration

@HiltAndroidApp
class BaseApp : Application(), Configuration.Provider {


    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()


    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}