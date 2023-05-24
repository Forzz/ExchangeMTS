package com.forzz.exchangermts.data.remote

import com.forzz.exchangermts.data.remote.dto.CurrenciesDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET()
    fun getCurrencies(
        @Query("apikey") apiKey: String,
        @Query("currencies") currencies: String?,
        @Query("base_currency") baseCurrency: String?
    ): Single<CurrenciesDTO>
}