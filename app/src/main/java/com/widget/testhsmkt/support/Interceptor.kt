package com.widget.testhsmkt.support

import java.util.Hashtable

class Interceptor {
    private var counter_ = 0
    private val container_ = Hashtable<Int, Any>()

    fun putTicket(`object`: Any?): Int {
        var result = INVALID_TICKET
        if (`object` == null) return result
        result = generate()
        if (result == INVALID_TICKET) return result
        if (container_.containsKey(result)) {
            result = INVALID_TICKET
            return result
        }
        try {
            container_[result] = `object`
        } catch (exception: Exception) {
        }

        return result
    }

    fun getTicket(ticket: Int): Any? {
        var result: Any? = null
        if (!container_.containsKey(ticket)) return result
        result = container_[ticket]
        container_.remove(ticket)
        return result
    }

    private fun generate(): Int {
        if (counter_ == MAX_VALUE) counter_ = 0
        return counter_++
    }

//    fun size(): Int {
//        return container_.size()
//    }

    companion object {
        const val INVALID_TICKET: Int = -1
        const val MAX_VALUE: Int = Int.MAX_VALUE
    }
}
