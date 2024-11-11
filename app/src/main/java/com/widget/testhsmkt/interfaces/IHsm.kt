package com.widget.testhsmkt.interfaces

import com.widget.testhsmkt.hsm.QEvent

interface IHsm {
    fun mediator(): IMediator?
    fun setMediator(mediator: IMediator?)
    fun init()
    fun dispatch(event: QEvent?)
}
