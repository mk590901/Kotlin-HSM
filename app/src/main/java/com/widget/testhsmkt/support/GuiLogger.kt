package com.widget.testhsmkt.support

import android.util.Log
import com.widget.testhsmkt.MainActivity
import com.widget.testhsmkt.interfaces.ILogger

class GuiLogger(activity: MainActivity) : ILogger {
    val TAG: String = "hsm"
    val _logger: MutableList<String> = ArrayList()
    val activity: MainActivity = activity

    override fun trace(string: String?) {
        _logger.add(string!!)
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
            _logger.add(label!!)
        }
    }

    override fun printTrace() {
        val text = string()
        Log.d(TAG, text)
        activity.addStringToRecyclerView(text)
    }
}
