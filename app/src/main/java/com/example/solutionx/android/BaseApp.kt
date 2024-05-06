package com.example.solutionx.android

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
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .setWorkerFactory(workerFactory)
            .build()


    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}