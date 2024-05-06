package com.pinest.config

import mu.KotlinLogging
import org.slf4j.Logger

class LoggingSupport {
    companion object {
        val <reified T> T.log: Logger
            inline get() = KotlinLogging.logger { T::class.java.name }
    }
}
