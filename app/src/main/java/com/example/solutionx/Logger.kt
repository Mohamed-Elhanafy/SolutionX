package com.example.solutionx

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.IOException

class Logger(private val context: Context) {

    fun log(message: String) {
        when (BuildConfig.FLAVOR) {
            "logCat" -> {
                Log.i("LogCat", "$message")
            }

            "logWriter" -> {
                writeToLogfile("$message")
            }

            "production" -> {
                // No logging for production flavor
            }
        }
    }

    private fun writeToLogfile(message: String) {
        // Write logs to file
        val logFile = getLogFile()
        try {
            val writer = FileWriter(logFile, true)
            writer.append(message + "\n")
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            Log.e("MainActivity", "Error writing to log file: ${e.message}")
        }
    }

    private fun getLogFile(): File {
        // Define the log file path
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "SolutionX")
        directory.mkdirs()
        return File(directory, "logfile.txt")
    }
}