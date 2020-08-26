package com.sungjae.portfolio.mapper

/* 구현을 dataClass에 하는 것과 impl에 하는 것 장단이 존재 */
interface ContentDataMapper<T, E> {

    fun T.toDomain(): E
    fun E.fromDomain(): T
}