package com.sungjae.portfolio.local.mapper

/* 구현을 dataClass에 하는 것과 impl에 하는 것 장단이 존재 */
interface ContentLocalMapper<L, E, D> {

    fun L.toDomain(): E
    fun D.fromData(type: String, query: String): L
}