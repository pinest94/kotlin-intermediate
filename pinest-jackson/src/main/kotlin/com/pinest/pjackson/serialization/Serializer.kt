package com.pinest.pjackson.serialization

import kotlin.reflect.full.memberProperties

fun serialize(obj: Any): String = buildString { serializeObject(obj) }

/***
 * runtime 상황에서 obj를 분석하여(using reflection) 객체의 property name, value를 추출하고
 * name: value형태로 json string 생성
 */
fun StringBuilder.serializeObject(obj: Any) {
    append("{")

    val kClass = obj::class
    val properties = kClass.memberProperties

    properties.forEachIndexed { index, name ->
        if(index < properties.size) { 
    }

    append("}")
}
