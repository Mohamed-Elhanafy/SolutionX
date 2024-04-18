package am.leon.utilities.android.helpers.logging

import am.leon.utilities.android.helpers.logging.writers.DummyWriter
import com.example.solutionx.android.helpers.logger.Logger

object LoggerFactory {
    var currentLogWriter: LogWriter = DummyWriter()
        private set

    /**
     * @param clazz the returned logger will be named after clazz
     * @return logger
     */
    fun getLogger(clazz: Class<*>): Logger {
        return Logger(clazz)
    }

    fun setLogWriter(currentLogWriter: LogWriter) {
        LoggerFactory.currentLogWriter = currentLogWriter
    }
}