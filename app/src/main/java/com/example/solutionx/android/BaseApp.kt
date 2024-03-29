package com.example.solutionx.android

import am.leon.utilities.android.helpers.logging.LoggerFactory
import am.leon.utilities.android.helpers.logging.writers.DummyWriter
import am.leon.utilities.android.helpers.logging.writers.FileWriter
import am.leon.utilities.android.helpers.logging.writers.LogcatWriter
import android.app.Application
import com.example.solutionx.BuildConfig
import com.example.solutionx.android.helpers.logger.LoggerProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }

}