package com.pinest.scheduler

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {
    val ex = Example6()
    ex.task()
}

class Example1 {
    fun task() {
        Flux.fromArray(arrayOf(1, 3, 5, 7))
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext { log.info("# doOnNext: {}", it) }
            .doOnSubscribe { log.info("# doOnSubscribe") } // subscribe 시점 thread check
            .subscribe { log.info("# onNext: {}", it) }

        Thread.sleep(500L)
    }
}