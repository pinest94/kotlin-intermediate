package com.pinest.sinks

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

fun main() {
    val ex = Example1()
    ex.task()
}

class Example1 {
    fun task() {
        val tasks = 6
        Flux.create {
            IntStream
                .range(1, tasks)
                .forEach { n ->
                    it.next(doTask(n))
                }
        }
            .subscribeOn(Schedulers.boundedElastic())
            .doOnNext {
                log.info("# create(): {}", it)
            }
            .publishOn(Schedulers.parallel())
            .map { "$it success!" }
            .doOnNext { log.info("# map(): {}", it) }
            .publishOn(Schedulers.parallel())
            .subscribe { log.info("# onNext: {}", it) }

        Thread.sleep(500L)
    }

    private fun doTask(taskNo: Int): String {
        return "task $taskNo result"
    }
}
