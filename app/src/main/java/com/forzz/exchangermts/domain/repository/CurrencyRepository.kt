package com.forzz.exchangermts.domain.repository

import com.forzz.exchangermts.domain.models.Currencies
import io.reactivex.Single

interface CurrencyRepository {

    fun getCurrencies(apiKey: String, currencies: String, baseCurrency: String?): Single<Currencies>
}