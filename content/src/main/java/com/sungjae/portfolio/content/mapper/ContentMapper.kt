package com.sungjae.portfolio.content.mapper

interface ContentMapper<T, E> {

    fun toData(data: E): T
    fun fromData(data: T): E
}