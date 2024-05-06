package com.pinest.scheduler

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {
    val ex = Example6()
    ex.task()
}

class Example3 {
    fun task() {
        val startTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        var endTime = 0L
        Flux.range(1, 100000)
            .parallel()
            .runOn(Schedulers.parallel())
            .subscribe(
                { log.info("# onNext: {}", it) },
                {},
                { endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() }
            )

        Thread.sleep(2000L)
        log.info("elapsed time: {}ms", (endTime - startTime))
    }
}