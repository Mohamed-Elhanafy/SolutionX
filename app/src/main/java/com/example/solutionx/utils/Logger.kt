package com.example.solutionx.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import com.example.solutionx.BuildConfig
import java.io.File
import java.io.FileWriter
import java.io.IOException

class Logger(private val context: Context) {

    fun log(tag: String = "LogCat", message: String) {
        when (BuildConfig.FLAVOR) {
            "logCat" -> {
                Log.i(tag, "$message")
            }

            "logWriter" -> {
                writeToLogfile(tag, "$message")
            }

            "production" -> {
            }
        }
    }

    private fun writeToLogfile(tag: String, message: String) {
        // Write logs to file
        val logFile = getLogFile()
        try {
            val writer = FileWriter(logFile, true)
            writer.append("$tag:$message\n")
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            Log.e("MainActivity", "Error writing to log file: ${e.message}")
        }
    }

    private fun getLogFile(): File {
        // Define the log file path
        val directory =
            File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "SolutionX")
        directory.mkdirs()
        return File(directory, "logfile.txt")
    }
}