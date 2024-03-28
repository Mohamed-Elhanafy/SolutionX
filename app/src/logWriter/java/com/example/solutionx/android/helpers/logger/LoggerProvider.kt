package com.example.solutionx.android.helpers.logger

import am.leon.utilities.android.helpers.logging.LoggerFactory
import am.leon.utilities.android.helpers.logging.writers.FileWriter
import com.example.solutionx.BuildConfig

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(
            FileWriter(
                folderName = tagKey,
                fileName = "SolutionX-Full-logFile",
                tagKey = tagKey,
                isDebugEnabled = BuildConfig.DEBUG
            )
        )
    }
}