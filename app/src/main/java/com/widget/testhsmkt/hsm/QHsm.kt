package com.widget.testhsmkt.hsm

import com.widget.testhsmkt.interfaces.IMediator

abstract class QHsm {
    private var state_: QState? = null
    private val path_ = arrayOfNulls<QState>(MAX_NEST_DEPTH)

    interface QState {
        fun handler(e: QEvent?): QState?
    }

    abstract fun init(e: QEvent?)

    protected fun init_tran(initial: QState?) {
        state_ = initial

        var s: QState? = top // an HSM starts in the top state
        do {                                       // drill into the target...
            var ip: Int = 0 // transition entry path index
            var t = state_
            path_[0] = t
            t = t!!.handler(EMPTY_EVT)
            while (t !== s) {
                path_[++ip] = t
                t = t!!.handler(EMPTY_EVT)
            }
            assert(
                ip < MAX_NEST_DEPTH // entry path must not overflow
            )
            do {       // retrace the entry path in reverse (desired) order...
                path_[ip.toInt()]!!.handler(ENTRY_EVT) // enter path_[ip]
            } while (--ip >= 0)
            s = state_
        } while (s!!.handler(INIT_EVT) == null)
    }

    protected fun Q_TRAN(target: QState?) {
        state_ = target
    }

    fun dispatch(e: QEvent?) {
        var s: QState?
        var t = state_

        path_[1] = t // save the current state in case a transition is taken
        state_ = null // make sure that a transition will be noticed

        do {                            // process the event hierarchically...
            s = t
            t = s!!.handler(e) // invoke state handler through pointer s
        } while (t != null)

        if (state_ != null) {                             // transition taken?
            val src = s!! // the source of the transition
            var ip: Int = -1 // transition entry path index
            var iq: Int // helper transition entry path index

            path_[0] = state_ // save the new state (target of tran.)
            state_ = path_[1] // restore the current state

            // exit current state to the transition source path_[1]...
            s = path_[1]
            while (s !== src) {
                t = s!!.handler(EXIT_EVT)
                s = // exit action unhandled
                    t // t points to superstate
                        ?: // exit action handled
                                s.handler(EMPTY_EVT) // find out the superstate
            }

            t = path_[0]

            if (src === t) {   // (a) check source==target (transition to self)
                src.handler(EXIT_EVT) // exit the source
                ip = 0 // enter the target
            } else {
                t = t!!.handler(EMPTY_EVT) // superstate of target
                if (src === t) {             // (b) check source==target->super
                    ip = 0 // enter the target
                } else {
                    s = src.handler(EMPTY_EVT) // superstate of src
                    if (s === t) {    // (c) check source->super==target->super
                        src.handler(EXIT_EVT) // exit the source
                        ip = 0 // enter the target
                    } else {
                        if (s === path_[0]) { // (d) check source->super==target
                            src.handler(EXIT_EVT) // exit the source
                        } else {  // (e) rest of source==target->super->super...
                            // and store the entry path along the way

                            iq = 0 // indicate that LCA not found
                            ip = 1 // enter target and its superstate
                            path_[1] = t // save the superstate of target
                            t = t!!.handler(EMPTY_EVT)
                            while (t != null) {
                                path_[++ip] = t // store the entry path
                                if (t === src) {           // is it the source?
                                    iq = 1 // indicate that LCA found
                                    // entry path must not overflow
                                    assert(ip < MAX_NEST_DEPTH)
                                    --ip // do not enter the source
                                    t = null // terminate the loop
                                } else {  // it is not the source, keep going up
                                    t = t.handler(EMPTY_EVT)
                                }
                            }
                            if (iq == 0) {           // the LCA not found yet?

                                // entry path must not overflow

                                assert(ip < MAX_NEST_DEPTH)

                                src.handler(EXIT_EVT) // exit the source

                                // (f) check the rest of source->super
                                //             == target->super->super...
                                iq = ip
                                do {
                                    if (s === path_[iq]) {  // is this the LCA?
                                        t = s // indicate that LCA is found
                                        ip = (iq - 1) //do not enter LCA
                                        iq = -1 // terminate the loop
                                    } else {
                                        --iq // try lower superstate of target
                                    }
                                } while (iq >= 0)

                                if (t == null) {         // LCA not found yet?
                                    // (g) check each source->super->...
                                    // for each target->super...

                                    do {
                                        t = s!!.handler(EXIT_EVT) // exit s
                                        s = // unhandled?
                                            t // t points to super of s
                                                ?: // exit action handled
                                                        s.handler(EMPTY_EVT)
                                        iq = ip
                                        do {
                                            if (s === path_[iq]) {   // is LCA?
                                                // do not enter LCA
                                                ip = (iq - 1)
                                                iq = -1 // break inner loop
                                                s = null // break outer loop
                                            } else {
                                                --iq
                                            }
                                        } while (iq >= 0)
                                    } while (s != null)
                                }
                            }
                        }
                    }
                }
            }
            // retrace the entry path in reverse (desired) order...
            while (ip >= 0) {
                path_[ip]!!.handler(ENTRY_EVT) // enter path_[ip]
                --ip
            }
            s = path_[0] // stick the target into register
            state_ = s // update the current state

            // drill into the target hierarchy...
            while (s!!.handler(INIT_EVT) == null) {
                t = state_

                path_[0] = t
                ip = 0
                t = t!!.handler(EMPTY_EVT)
                while (t !== s
                ) {
                    path_[++ip] = t
                    t = t!!.handler(EMPTY_EVT)
                }
                assert(
                    ip < MAX_NEST_DEPTH // entry path must not overflow
                )

                do {   // retrace the entry path in reverse (correct) order...
                    path_[ip.toInt()]!!.handler(ENTRY_EVT) // enter path_[ip]
                } while ((--ip) >= 0)
                s = state_
            }
        } else {
            state_ = path_[1] // restore the current state
        }
    }

    companion object {
        const val Q_EMPTY_SIG: Int = 0
        const val Q_INIT_SIG: Int = 1
        const val Q_ENTRY_SIG: Int = 2
        const val Q_EXIT_SIG: Int = 3
        const val Q_USER_SIG: Int = 4

        private const val MAX_NEST_DEPTH = 256 //	6

        private val EMPTY_EVT = QEvent(Q_EMPTY_SIG)
        private val INIT_EVT = QEvent(Q_INIT_SIG)
        private val ENTRY_EVT = QEvent(Q_ENTRY_SIG)
        private val EXIT_EVT = QEvent(Q_EXIT_SIG)

        val top: QState = object : QState {
            override fun handler(e: QEvent?): QState? {
                return null
            }
        }
    }

    fun top(): QState {
        return top
    }

}
