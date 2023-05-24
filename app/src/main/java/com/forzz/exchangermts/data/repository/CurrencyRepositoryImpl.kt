package com.forzz.exchangermts.data.repository

import com.forzz.exchangermts.data.remote.CurrencyApi
import com.forzz.exchangermts.domain.models.Currencies
import com.forzz.exchangermts.domain.models.Currency
import com.forzz.exchangermts.domain.models.MetaData
import com.forzz.exchangermts.domain.repository.CurrencyRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi
) : CurrencyRepository {
    override fun getCurrencies(
        apiKey: String,
        currencies: String?,
        baseCurrency: String?
    ): Single<Currencies> {
        return api.getCurrencies(apiKey, currencies, baseCurrency)
            .map { currenciesDTO ->
                val meta = MetaData(currenciesDTO.meta.lastUpdatedAt)
                val data = currenciesDTO.data.mapValues { (_, currencyDTO) ->
                    Currency(currencyDTO.code, currencyDTO.value)
                }
                Currencies(meta, data)
            }
    }
}