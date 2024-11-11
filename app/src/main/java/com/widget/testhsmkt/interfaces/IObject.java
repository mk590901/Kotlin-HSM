package com.widget.testhsmkt.interfaces;
import com.widget.testhsmkt.support.ObjectEvent;

public interface IObject {
	void 		done		(ObjectEvent signal);
	IMediator 	mediator	();
	void 		setMediator	(IMediator mediator);
	void 		init		();
}
