package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Sinks

fun main() {
    val ex = Example4()
    ex.task()
}

class Example4 {
    fun task() {
        val unicastSink = Sinks.many().unicast().onBackpressureBuffer<Int>()
        val fluxView = unicastSink.asFlux()

        unicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST)
        unicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST)

        fluxView.subscribe { log.info("# Subscriber1 {}", it) }

        unicastSink.emitNext(3 , Sinks.EmitFailureHandler.FAIL_FAST)
        // fluxView.subscribe { log.info("# Subscriber2 {}", it) }
    }

}