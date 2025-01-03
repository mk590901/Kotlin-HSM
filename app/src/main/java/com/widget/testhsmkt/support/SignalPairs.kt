package com.widget.testhsmkt.support

import com.widget.testhsmkt.hsm.QHsm
import java.util.Hashtable

class SignalPairs {
    private val container_ = Hashtable<Int, Int>()

    fun add(objectAction: Int, hsmSignal: Int): Boolean {
        var result = false

        if (container_.containsKey(objectAction)) return result

        try {
            container_[objectAction] = hsmSignal
            result = true
        } catch (exception: Exception) {
        }
        return result
    }

    fun get(objectAction: Int): Int {
        var result: Int = QHsm.Q_EMPTY_SIG
        if (!container_.containsKey(objectAction)) return result

        result = container_[objectAction]!!
        return result
    }
}
