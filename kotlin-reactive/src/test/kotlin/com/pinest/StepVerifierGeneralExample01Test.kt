package com.pinest

import com.pinest.test.GeneralTestExample
import com.pinest.test.TimeBasedTestExample
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import reactor.test.StepVerifierOptions
import reactor.test.scheduler.VirtualTimeScheduler
import java.time.Duration

class StepVerifierGeneralExample01Test {

    @Test
    fun sayHelloReactorTest() {
        StepVerifier
            .create(Mono.just("Hello Reactor"))
            .expectNext("Hello Reactor")
            .expectComplete()
            .verify()
    }

    @Test
    fun sayHelloTest() {
        StepVerifier
            .create(GeneralTestExample.sayHello())
            .expectSubscription()
            .`as`("# expect subscription")
            .expectNext("Hello")
            .`as`("# expect Hi")
            .expectNext("Reactor")
            .`as`("# expect Reactor")
            .verifyComplete()
    }

    @Test
    fun divideByTwoTest() {
        val source = Flux.just(2, 4, 6, 8, 10)
        StepVerifier
            .create(GeneralTestExample.divideByTwo(source))
            .expectSubscription()
//            .expectNext(1)
//            .expectNext(2)
//            .expectNext(3)
//            .expectNext(4)
            .expectNext(1, 2, 3, 4)
            .expectError()
            .verify()
    }

    @Test
    fun takeNumberTest() {
        val source = Flux.range(0, 1000)
        StepVerifier
            .create(
                GeneralTestExample.takeNumber(source, 100),
                StepVerifierOptions.create().scenarioName("Verify from 0 to 99"),
            )
            .expectSubscription()
            .expectNext(0)
            .expectNextCount(98)
            .expectNext(99)
            .expectComplete()
            .verify()
    }

    @Test
    fun getCOVID19CountTest() {
        StepVerifier
            .withVirtualTime {
                TimeBasedTestExample.getCOVID19Count(
                    Flux.interval(Duration.ofHours(1L)).take(1),
                )
            }
            .expectSubscription()
            .then {
                VirtualTimeScheduler
                    .get()
                    .advanceTimeBy(Duration.ofHours(1L))
            }
            .expectNextCount(11)
            .expectComplete()
            .verify()
    }
}
