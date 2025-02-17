package dev.achmad.core.util

import android.util.Log

inline fun logcat(
    priority: LogPriority = LogPriority.DEBUG,
    tag: String = priority.name,
    throwable: Throwable? = null,
    message: () -> String = { "" },
) {
    when(priority) {
        LogPriority.VERBOSE -> Log.v(tag, message(), throwable)
        LogPriority.DEBUG -> Log.d(tag, message(), throwable)
        LogPriority.INFO -> Log.i(tag, message(), throwable)
        LogPriority.WARN -> Log.w(tag, message(), throwable)
        LogPriority.ERROR -> Log.e(tag, message(), throwable)
    }
}

enum class LogPriority { VERBOSE, DEBUG, INFO, WARN, ERROR }
