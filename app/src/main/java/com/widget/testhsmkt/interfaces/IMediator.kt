package com.widget.testhsmkt.interfaces

interface IMediator {
    fun hsm(): IHsm?
    fun setHsm(hsm: IHsm?)
    fun hsmDone(state: String?, signal: Int, data: Any?)
    fun init()
    fun setLogger(logger: ILogger?) //	4 debug
    fun execute(state: String?, signal: Int, data: Int)
    fun objDone(signal: Int, data: Any?)
}
