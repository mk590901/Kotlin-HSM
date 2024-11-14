package com.widget.testhsmkt.implementation

import com.widget.testhsmkt.hsm.QEvent;
import com.widget.testhsmkt.interfaces.IHsm;
import com.widget.testhsmkt.interfaces.IMediator;

class Samek_9BWrapper (private val entity_: Samek_9BQHsmScheme?, private var mediator_: IMediator?) : IHsm {

	init {
		mediator_?.setHsm(this)
	}

	override fun mediator(): IMediator? { return mediator_ }

	override fun setMediator(mediator: IMediator?) { mediator_ = mediator; }

	override fun init() {
		entity_?.init(QEvent(Samek_9BQHsmScheme.INIT))
	}
	override fun dispatch(event: QEvent?) {
		entity_?.dispatch(event)
	}
}

