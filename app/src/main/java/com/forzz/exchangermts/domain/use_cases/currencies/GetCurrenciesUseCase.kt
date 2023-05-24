package com.forzz.exchangermts.domain.use_cases.currencies

import com.forzz.exchangermts.domain.models.Currencies
import com.forzz.exchangermts.domain.repository.CurrencyRepository
import com.forzz.exchangermts.domain.use_cases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
    private val repository: CurrencyRepository
) : SingleUseCase<Currencies>() {

    private lateinit var apiKey: String
    private var currencies: String? = null
    private var baseCurrency: String? = "RUB"

    fun saveData(apiKey: String, currencies: String?, baseCurrency: String) {
        this.apiKey = apiKey
        this.currencies = currencies
        this.baseCurrency = baseCurrency
    }

    override fun buildUseCaseSingle(): Single<Currencies> {
        return repository.getCurrencies(apiKey, currencies, baseCurrency)
    }
}