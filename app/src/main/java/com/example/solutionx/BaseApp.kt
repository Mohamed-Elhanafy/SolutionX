package com.example.solutionx

import am.leon.utilities.android.helpers.logging.LoggerFactory
import am.leon.utilities.android.helpers.logging.writers.DummyWriter
import am.leon.utilities.android.helpers.logging.writers.FileWriter
import am.leon.utilities.android.helpers.logging.writers.LogcatWriter
import android.app.Application
import android.util.Log

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()


        when (BuildConfig.FLAVOR) {
            "logCat" -> {
                LoggerFactory.setLogWriter(
                LogcatWriter(
                    "SolutionX",
                    BuildConfig.DEBUG
                ))
            }

            "logWriter" -> {
                LoggerFactory.setLogWriter(
                    FileWriter(
                        "SolutionX",
                        "SolutionXLogFile",
                        "test_logger",
                        BuildConfig.DEBUG
                    )
                )
            }

            "production" -> {
                LoggerFactory.setLogWriter(
                    DummyWriter()
                )
            }
        }

    }

}