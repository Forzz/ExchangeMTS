package com.forzz.exchangermts.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrenciesDTO(
    val meta: MetaDataDTO,
    val data: Map<String, CurrencyDTO>
)

data class MetaDataDTO(
    @SerializedName("last_updated_at")
    val lastUpdatedAt: String
)

data class CurrencyDTO(
    val code: String,
    val value: Double
)