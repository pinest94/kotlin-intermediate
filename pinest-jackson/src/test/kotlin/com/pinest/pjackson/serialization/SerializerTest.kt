package com.pinest.pjackson.serialization

import com.pinest.pjackson.domain.Member
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerTest {

    @Test
    fun serializeTest1() {
        val member = Member(id = 1L, name = "Andrew")
        val jsonData = serialize(member)

        assertEquals("""
            "id": 1,
            "name": "Andrew"
        """.trimIndent(), jsonData)
    }
}