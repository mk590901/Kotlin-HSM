package com.widget.testhsmkt.support

import java.util.Hashtable

class Commands {
    private val container_ = Hashtable<String, Command>()

//    fun number(): Int {
//        return container_.size()
//    }

    fun add(state: String, signal: Int, executor: Executor): Boolean {
        var result = false
        val key = getKey(state, signal)
        if (container_.containsKey(key)) return result
        try {
            container_[key] = Command(state, signal, executor)
            result = true
        } catch (exception: Exception) {
        }
        return result
    }

    fun get(state: String, signal: Int): Command? {
        var result: Command? = null
        val key = getKey(state, signal)
        if (!container_.containsKey(key)) return result
        result = container_[key]
        return result
    }

    fun getKey(state: String, signal: Int): String {
        val result = "$state$$signal"
        return result
    }
}
