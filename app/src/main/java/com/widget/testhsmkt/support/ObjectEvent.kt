package com.widget.testhsmkt.support

class ObjectEvent
    (event: Int, data: Any?) {
    private var event_ = 0x00
    private var data_: Any? = null

    init {
        event_ = event
        data_ = data
    }

    fun Event(): Int {
        return event_
    }

    fun Data(): Any? {
        return data_
    }
}
