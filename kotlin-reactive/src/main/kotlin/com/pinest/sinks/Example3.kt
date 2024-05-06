package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Sinks

fun main() {
    val ex = Example3()
    ex.task()
}

class Example3 {
    fun task() {
        val sinkOne = Sinks.one<String>()
        val mono = sinkOne.asMono()

        sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST)
        sinkOne.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST)

        mono.subscribe { log.info("# Subscriber1 {}", it) }
        mono.subscribe { log.info("# Subscriber2 {}", it) }
    }

}