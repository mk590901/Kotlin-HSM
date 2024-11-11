package com.widget.testhsmkt.hsm

class QEvent {
    var sig_: Int = 0
    var ticket_: Int = 0

    constructor() {
        sig_ = 0
        ticket_ = 0
    }

    constructor(s: Int) {
        sig_ = s
        ticket_ = 0
    }

    constructor(s: Int, ticket: Int) {
        sig_ = s
        ticket_ = ticket
    }
}