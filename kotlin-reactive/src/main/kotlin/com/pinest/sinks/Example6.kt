package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Sinks

fun main() {
    val ex = Example6()
    ex.task()
}

class Example6 {
    fun task() {
        val replaySink = Sinks.many().replay().limit<Int>(2)
        val fluxView = replaySink.asFlux()

        replaySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST)
        replaySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST)
        replaySink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST)

        fluxView.subscribe { log.info("# Subscriber1 {}", it) }

        replaySink.emitNext(4 , Sinks.EmitFailureHandler.FAIL_FAST)

        fluxView.subscribe { log.info("# Subscriber2 {}", it) }
    }

}