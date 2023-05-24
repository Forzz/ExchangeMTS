package com.forzz.exchangermts.domain.models

data class Currencies(
    val meta: MetaData,
    val data: Map<String, Currency>
)

data class MetaData(
    val lastUpdatedAt: String
)

data class Currency(
    val code: String,
    val value: Double
)
