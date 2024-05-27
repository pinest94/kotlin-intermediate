package com.pinest.context

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {
    val ex = Example2()
    ex.task()
}

class Example2 {
    fun task() {
        val key1 = "company"

        val mono = Mono.deferContextual { ctx -> Mono.just("Company ${ctx.get<String>(key1)}") }
            .publishOn(Schedulers.parallel())

        mono.contextWrite { it.put(key1, "Apple") }
            .contextWrite { it.put(key1, "Tesla") }
            .contextWrite { it.put(key1, "Palantir") }
            .subscribe { log.info("# subscribe1 onNext: {}", it) }

        mono.contextWrite { it.put(key1, "NVIDIA") }
            .subscribe { log.info("# subscribe2 onNext: {}", it) }

        Thread.sleep(100L)
    }
}
