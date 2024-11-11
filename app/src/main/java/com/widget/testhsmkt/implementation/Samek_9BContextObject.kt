//	Class Samek_9BContextObject: file automatically generated at 2024-11-06 13:23:31
package com.widget.testhsmkt.implementation

import com.widget.testhsmkt.interfaces.ILogger
import com.widget.testhsmkt.interfaces.IMediator
import com.widget.testhsmkt.interfaces.IObject
import com.widget.testhsmkt.support.ObjectEvent

class Samek_9BContextObject(logger: ILogger) : IObject {
    var mediator_: IMediator? = null
    var logger_: ILogger = logger
    override fun done(signal: ObjectEvent) {
        mediator_?.objDone(signal.Event(), signal.Data())
    }

    override fun init() {
        mediator_?.init()
    }

    override fun mediator(): IMediator? {
        return mediator_
    }

    override fun setMediator(mediator: IMediator?) {
        mediator_ = mediator
    }

    fun getEventId(eventName: String): Int {
        var result = 0
        when (eventName) {
            "b" -> result = b
            "a" -> result = a
            "d" -> result = d
            "h" -> result = h
            "g" -> result = g
            "e" -> result = e
            "c" -> result = c
            "f" -> result = f
        }
        return result
    }

    fun OnInit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "top-QHsmScheme.INIT_SIG" else "top-QHsmScheme.INIT_SIG[$data]")
        return result
    }

    fun OnS2Entry(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s2-QHsm.Q_ENTRY_SIG" else "s2-QHsm.Q_ENTRY_SIG[$data]")
        return result
    }

    fun OnS2Exit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s2-QHsm.Q_INIT_SIG" else "s2-QHsm.Q_INIT_SIG[$data]")
        return result
    }

    fun OnS2c(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s2-QHsmScheme.c" else "s2-QHsmScheme.c[$data]")
        return result
    }

    fun OnS2f(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s2-QHsmScheme.f" else "s2-QHsmScheme.f[$data]")
        return result
    }

    fun OnS21Entry(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s21-QHsm.Q_ENTRY_SIG" else "s21-QHsm.Q_ENTRY_SIG[$data]")
        return result
    }

    fun OnS21Exit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s21-QHsm.Q_INIT_SIG" else "s21-QHsm.Q_INIT_SIG[$data]")
        return result
    }

    fun OnS21b(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s21-QHsmScheme.b" else "s21-QHsmScheme.b[$data]")
        return result
    }

    fun OnS21h(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s21-QHsmScheme.h" else "s21-QHsmScheme.h[$data]")
        return result
    }

    fun OnS211Entry(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s211-QHsm.Q_ENTRY_SIG" else "s211-QHsm.Q_ENTRY_SIG[$data]")
        return result
    }

    fun OnS211Exit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s211-QHsm.Q_EXIT_SIG" else "s211-QHsm.Q_EXIT_SIG[$data]")
        return result
    }

    fun OnS211g(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s211-QHsmScheme.g" else "s211-QHsmScheme.g[$data]")
        return result
    }

    fun OnS1Entry(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsm.Q_ENTRY_SIG" else "s1-QHsm.Q_ENTRY_SIG[$data]")
        return result
    }

    fun OnS1Exit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsm.Q_INIT_SIG" else "s1-QHsm.Q_INIT_SIG[$data]")
        return result
    }

    fun OnS1b(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsmScheme.b" else "s1-QHsmScheme.b[$data]")
        return result
    }

    fun OnS1c(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsmScheme.c" else "s1-QHsmScheme.c[$data]")
        return result
    }

    fun OnS1f(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsmScheme.f" else "s1-QHsmScheme.f[$data]")
        return result
    }

    fun OnS1a(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsmScheme.a" else "s1-QHsmScheme.a[$data]")
        return result
    }

    fun OnS1d(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s1-QHsmScheme.d" else "s1-QHsmScheme.d[$data]")
        return result
    }

    fun OnS11Entry(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s11-QHsm.Q_ENTRY_SIG" else "s11-QHsm.Q_ENTRY_SIG[$data]")
        return result
    }

    fun OnS11Exit(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s11-QHsm.Q_EXIT_SIG" else "s11-QHsm.Q_EXIT_SIG[$data]")
        return result
    }

    fun OnS11g(data: Any?): Boolean {
        val result = false
        logger_.trace(if (data == null) "s11-QHsmScheme.g" else "s11-QHsmScheme.g[$data]")
        return result
    }

    companion object {
        const val APP_START_ENUM: Int = 1
        const val INIT: Int = APP_START_ENUM
        const val FINAL: Int = APP_START_ENUM + 1
        const val b: Int = APP_START_ENUM + 2
        const val a: Int = APP_START_ENUM + 3
        const val d: Int = APP_START_ENUM + 4
        const val h: Int = APP_START_ENUM + 5
        const val g: Int = APP_START_ENUM + 6
        const val e: Int = APP_START_ENUM + 7
        const val c: Int = APP_START_ENUM + 8
        const val f: Int = APP_START_ENUM + 9
    }
}
