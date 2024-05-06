package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

fun main() {
    val ex = Example2()
    ex.task()
}

class Example2 {

    private fun doTask(n: Int) = "task $n result"

    fun task() {
        val tasks = 6
        val unicastSink = Sinks.many().unicast().onBackpressureBuffer<String>()

        val fluxView = unicastSink.asFlux()
        IntStream.range(1, tasks)
            .forEach { n ->
                try {
                    Thread {
                        unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST)
                        log.info("# emitted: {}", n)
                    }.start()
                    Thread.sleep(100L)
                } catch (ex: InterruptedException) {
                    log.error(ex.message)
                }
            }

        fluxView
            .publishOn(Schedulers.parallel())
            .map { "$it success!" }
            .doOnNext { log.info("# map(): {}", it) }
            .publishOn(Schedulers.parallel())
            .subscribe { log.info("# onNext: {}", it) }

        Thread.sleep(200L)
    }
}