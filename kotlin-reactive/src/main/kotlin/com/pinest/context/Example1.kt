package com.pinest.context

import com.pinest.config.LoggingSupport.Companion.log
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

fun main() {
    val ex = Example1()
    ex.task()
}

class Example1 {
    fun task() {
        Mono.deferContextual { ctx ->
            Mono.just("hello ${ctx.get<String>("firstName")}")
                .doOnNext { log.info("# just doOnNext : {}", it) }
        }
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .transformDeferredContextual { mono, ctx ->
                mono.map {
                    "$it ${ctx.get<String>("lastName")}"
                }
            }
            .contextWrite { it.put("firstName", "kim") }
            .contextWrite { it.put("lastName", "hansol") }
            .subscribe { log.info("# onNext: {}", it) }

        Thread.sleep(500L)
    }
}
