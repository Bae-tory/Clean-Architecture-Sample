package com.sungjae.portfolio.content.mapper

interface ContentMapper<T, E> {

    fun fromDomain(data: T): E
}