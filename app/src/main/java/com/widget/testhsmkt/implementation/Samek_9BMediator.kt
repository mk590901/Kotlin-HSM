//	Class Samek_9BMediator automatically generated by tMediatorJavaCompileVisitor at 2024-11-06 13:23:31
package com.widget.testhsmkt.implementation

import com.widget.testhsmkt.hsm.QEvent
import com.widget.testhsmkt.hsm.QHsm
import com.widget.testhsmkt.interfaces.IHsm
import com.widget.testhsmkt.interfaces.ILogger
import com.widget.testhsmkt.interfaces.IMediator
import com.widget.testhsmkt.support.Commands
import com.widget.testhsmkt.support.Executor
import com.widget.testhsmkt.support.Interceptor
import com.widget.testhsmkt.support.SignalPairs

class Samek_9BMediator(
    private val context_: Samek_9BContextObject,
    private var interceptor_: Interceptor,
    private var logger_: ILogger?
) :
    IMediator {
    private var hsm_: IHsm? = null
    private var commands_: Commands? = null
    private var connector_: SignalPairs? = null

    init {
        context_.setMediator(this)
        createCommands()
        createConnector()
    }


    private fun createConnector() {
        connector_ = SignalPairs()
        connector_!!.Add(Samek_9BContextObject.INIT, Samek_9BQHsmScheme.INIT)
        connector_!!.Add(Samek_9BContextObject.FINAL, Samek_9BQHsmScheme.FINAL)
        connector_!!.Add(Samek_9BContextObject.b, Samek_9BQHsmScheme.b)
        connector_!!.Add(Samek_9BContextObject.a, Samek_9BQHsmScheme.a)
        connector_!!.Add(Samek_9BContextObject.d, Samek_9BQHsmScheme.d)
        connector_!!.Add(Samek_9BContextObject.h, Samek_9BQHsmScheme.h)
        connector_!!.Add(Samek_9BContextObject.g, Samek_9BQHsmScheme.g)
        connector_!!.Add(Samek_9BContextObject.e, Samek_9BQHsmScheme.e)
        connector_!!.Add(Samek_9BContextObject.c, Samek_9BQHsmScheme.c)
        connector_!!.Add(Samek_9BContextObject.f, Samek_9BQHsmScheme.f)
    }

    override fun hsm(): IHsm? {
        return hsm_
    }

    override fun setHsm(hsm: IHsm?) {
        hsm?.setMediator(this)
        hsm_ = hsm
    }

    override fun hsmDone(state: String?, signal: Int, data: Any?) {
    }

    override fun init() {
            logger_?.clear("[INIT]: ")
        hsm_?.init()
            logger_?.printTrace()
    }

    override fun objDone(signal: Int, objectData: Any?) {
        val hsmEvt = eventObj2Hsm(signal)
        val data = interceptor_.putTicket(objectData)
        val e = QEvent(hsmEvt, data)
            logger_?.clear(decodeSignal(hsmEvt) + ": ")
        hsm_?.dispatch(e)
            logger_!!.printTrace()
    }

    fun setInterceptor(interceptor: Interceptor) {
        interceptor_ = interceptor
    }

    override fun setLogger(logger: ILogger?) {
        logger_ = logger
    }

    private fun eventObj2Hsm(objectSignal: Int): Int {
        return connector_!!.Get(objectSignal)
    }

    private fun decodeSignal(signal: Int): String {
        var result = "?Signal"
        when (signal) {
            QHsm.Q_EMPTY_SIG -> result = "@Q_EMPTY_SIG"
            QHsm.Q_INIT_SIG -> result = "@Q_INIT_SIG"
            QHsm.Q_ENTRY_SIG -> result = "@Q_ENTRY_SIG"
            QHsm.Q_EXIT_SIG -> result = "@Q_EXIT_SIG"
            Samek_9BQHsmScheme.INIT -> result = "@INIT"
            Samek_9BQHsmScheme.FINAL -> result = "@FINAL"
            Samek_9BQHsmScheme.b -> result = "@b"
            Samek_9BQHsmScheme.a -> result = "@a"
            Samek_9BQHsmScheme.d -> result = "@d"
            Samek_9BQHsmScheme.h -> result = "@h"
            Samek_9BQHsmScheme.g -> result = "@g"
            Samek_9BQHsmScheme.e -> result = "@e"
            Samek_9BQHsmScheme.c -> result = "@c"
            Samek_9BQHsmScheme.f -> result = "@f"
        }
        return result
    }

    override fun execute(state: String?, signal: Int, ticket: Int) {
        val command = commands_?.get(state!!, signal)
        if (command == null) {
            val data = interceptor_.getTicket(ticket)
            if (data == null) {
                logger_?.trace(String.format("%s-%s", state, decodeSignal(signal)))
            } else {
                logger_?.trace(
                    String.format(
                        "%s-%s[%s]",
                        state,
                        decodeSignal(signal),
                        data.toString()
                    )
                )
            }
        } else {
            val result = command.executor_.execute(signal, ticket)
        }
    }

    fun createCommands() {
        try {
            commands_ = Commands()

            commands_?.add("init", Samek_9BQHsmScheme.INIT, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnInit(value)
                    return true
                }
            })


            commands_?.add("s2", QHsm.Q_ENTRY_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS2Entry(value)
                    return true
                }
            })

            commands_?.add("s2", QHsm.Q_EXIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS2Exit(value)
                    return true
                }
            })

            commands_?.add("s2", QHsm.Q_INIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS2Exit(value)
                    return true
                }
            })

            commands_?.add("s2", Samek_9BQHsmScheme.c, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS2c(value)
                    return true
                }
            })

            commands_?.add("s2", Samek_9BQHsmScheme.f, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS2f(value)
                    return true
                }
            })

            commands_?.add("s21", QHsm.Q_ENTRY_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS21Entry(value)
                    return true
                }
            })

            commands_?.add("s21", QHsm.Q_EXIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS21Exit(value)
                    return true
                }
            })

            commands_?.add("s21", QHsm.Q_INIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS21Exit(value)
                    return true
                }
            })

            commands_?.add("s21", Samek_9BQHsmScheme.b, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS21b(value)
                    return true
                }
            })

            commands_?.add("s21", Samek_9BQHsmScheme.h, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS21h(value)
                    return true
                }
            })

            commands_?.add("s211", QHsm.Q_ENTRY_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS211Entry(value)
                    return true
                }
            })

            commands_?.add("s211", QHsm.Q_EXIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS211Exit(value)
                    return true
                }
            })

            commands_?.add("s211", Samek_9BQHsmScheme.g, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS211g(value)
                    return true
                }
            })

            commands_?.add("s1", QHsm.Q_ENTRY_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1Entry(value)
                    return true
                }
            })


            commands_?.add("s1", QHsm.Q_EXIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1Exit(value)
                    return true
                }
            })

            commands_?.add("s1", QHsm.Q_INIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1Exit(value)
                    return true
                }
            })

            commands_?.add("s1", Samek_9BQHsmScheme.b, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1b(value)
                    return true
                }
            })

            commands_?.add("s1", Samek_9BQHsmScheme.c, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1c(value)
                    return true
                }
            })

            commands_?.add("s1", Samek_9BQHsmScheme.f, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1f(value)
                    return true
                }
            })

            commands_?.add("s1", Samek_9BQHsmScheme.a, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1a(value)
                    return true
                }
            })

            commands_?.add("s1", Samek_9BQHsmScheme.d, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS1d(value)
                    return true
                }
            })

            commands_?.add("s11", QHsm.Q_ENTRY_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS11Entry(value)
                    return true
                }
            })

            commands_?.add("s11", QHsm.Q_EXIT_SIG, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS11Exit(value)
                    return true
                }
            })

            commands_?.add("s11", Samek_9BQHsmScheme.g, object : Executor {
                override fun execute(signal: Int, ticket: Int): Boolean {
                    val value = interceptor_.getTicket(ticket)
                    context_.OnS11g(value)
                    return true
                }
            })
        }
        catch (exception: Exception) {
        }
    }
}
