package com.widget.testhsmkt.interfaces

interface ILogger {
    fun trace(string: String?)
    fun string(): String?
    fun toTrace(): String?
    fun clear(label: String?)
    fun printTrace()
}
