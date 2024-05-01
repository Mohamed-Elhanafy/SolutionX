package com.example.solutionx.android.helpers.logger

import am.leon.utilities.android.helpers.logging.writers.LogcatWriter
import com.example.solutionx.BuildConfig

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(LogcatWriter(tagKey, BuildConfig.DEBUG))
    }
}