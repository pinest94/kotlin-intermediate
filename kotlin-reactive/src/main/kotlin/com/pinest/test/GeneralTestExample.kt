package com.pinest.test

import reactor.core.publisher.Flux

class GeneralTestExample {

    companion object {
        fun sayHello() = Flux.just("Hello", "Reactor")
        fun divideByTwo(source: Flux<Int>) = source.zipWith(Flux.just(2, 2, 2, 2, 0)) { x, y -> x / y }
        fun takeNumber(source: Flux<Int>, n: Long) = source.take(n)
    }
}
