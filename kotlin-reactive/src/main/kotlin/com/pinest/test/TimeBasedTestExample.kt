package com.pinest.test

import reactor.core.publisher.Flux
import reactor.util.function.Tuple2
import reactor.util.function.Tuples

class TimeBasedTestExample {

    companion object {
        fun getCOVID19Count(source: Flux<Long>): Flux<Tuple2<String, Int>> {
            return source.flatMap {
                Flux.just(
                    Tuples.of("서울", 10),
                    Tuples.of("경기", 5),
                    Tuples.of("강원", 3),
                    Tuples.of("충청", 6),
                    Tuples.of("경상", 5),
                    Tuples.of("전라", 8),
                    Tuples.of("인천", 2),
                    Tuples.of("대전", 1),
                    Tuples.of("대구", 2),
                    Tuples.of("부산", 3),
                    Tuples.of("제주", 0),
                )
            }
        }

        fun getVoteCount(source: Flux<Long>): Flux<Tuple2<String, Int>> {
            return source.zipWith(
                Flux.just(
                    Tuples.of("중구", 15400),
                    Tuples.of("서초구", 20020),
                    Tuples.of("강서구", 32040),
                    Tuples.of("강동구", 14506),
                    Tuples.of("서대문구", 35650),
                ),
            ).map { it.t2 }
        }
    }
}
