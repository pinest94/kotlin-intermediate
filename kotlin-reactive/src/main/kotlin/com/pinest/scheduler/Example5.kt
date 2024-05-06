package com.pinest.scheduler

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {
    val ex = Example6()
    ex.task()
}

class Example5 {
    fun task() {
        doTask("task1")
            .subscribe { log.info("# onNext: {}", it) }

        doTask("task2")
            .subscribe { log.info("# onNext: {}", it) }
        Thread.sleep(500L)
    }

    private fun doTask(taskName: String): Flux<Int> {
        return Flux.range(1, 10)
            .publishOn(Schedulers.single())
            .filter { it > 5 }
            .doOnNext { log.info("# {} doOnNext: {}", taskName, it) }
            .map { it * 10 }
            .doOnNext { log.info("# {} doOnNext map: {}", taskName, it) }
    }
}