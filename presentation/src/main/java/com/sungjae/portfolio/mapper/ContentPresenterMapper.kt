package com.sungjae.portfolio.mapper

interface ContentPresenterMapper<T, E> {

    fun toDomain(data: T): E
}