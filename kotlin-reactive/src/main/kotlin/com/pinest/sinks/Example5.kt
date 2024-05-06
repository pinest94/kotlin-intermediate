package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Sinks

fun main() {
    val ex = Example6()
    ex.task()
}

class Example5 {
    fun task() {
        val multicastSink = Sinks.many().multicast().onBackpressureBuffer<Int>()
        val fluxView = multicastSink.asFlux()

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST)
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST)

        fluxView.subscribe { log.info("# Subscriber1 {}", it) }
        fluxView.subscribe { log.info("# Subscriber2 {}", it) }

        multicastSink.emitNext(3 , Sinks.EmitFailureHandler.FAIL_FAST)
    }

}