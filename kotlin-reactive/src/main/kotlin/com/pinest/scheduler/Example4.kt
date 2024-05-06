package com.pinest.scheduler

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {
    val ex = Example6()
    ex.task()
}

class Example4 {
    fun task() {
        Flux.range(1, 10)
            .publishOn(Schedulers.parallel())
            .filter { it > 5 }
            .doOnNext { log.info("# doOnNext: {}", it) }
            .publishOn(Schedulers.immediate())
            .map { it * 10 }
            .doOnNext { log.info("# doOnNext map: {}", it) }
            .subscribe { log.info("# onNext: {}", it) }

        Thread.sleep(500L)
    }
}