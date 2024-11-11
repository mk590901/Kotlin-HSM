package com.widget.testhsmkt.support

import android.util.Log
import com.widget.testhsmkt.interfaces.ILogger

class Logger : ILogger {
    val TAG: String = "hsm"
    val _logger: MutableList<String> = ArrayList()

    override fun trace(string: String?) {
        _logger.add(string!!)
        //Log.d(TAG, string());
        //Log.d(TAG, string);
    }

    override fun string(): String {
        var result = ""
        for (i in _logger.indices) {
            result += _logger[i] + (if ((i == (_logger.size - 1) || (i == 0))) "" else ";")
        }
        return result
    }

    override fun toTrace(): String {
        var result = ""
        if (_logger.size < 2) {
            return result
        }
        for (i in 1 until _logger.size) {
            result += _logger[i] + (if (i == (_logger.size - 1)) "" else " ")
        }
        return result
    }

    override fun clear(label: String?) {
        _logger.clear()
        if (!label!!.isEmpty()) {
            _logger.add(label)
        }
    }

    override fun printTrace() {
        Log.d(TAG, string())
    }
}
