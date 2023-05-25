package com.forzz.exchangermts.presentation.home_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.forzz.exchangermts.domain.models.Currencies
import com.forzz.exchangermts.domain.models.Currency
import com.forzz.exchangermts.domain.use_cases.currencies.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val apiKey: String
) : ViewModel() {

    val currencies = MutableLiveData<Currencies>()
    val isLoad = MutableLiveData<Boolean>()
    val currencyList= MutableLiveData<List<Currency>>()

    init {
        isLoad.value = false
    }

    fun saveData(currencies: String, baseCurrency: String) =
        getCurrenciesUseCase.saveData(apiKey, currencies, baseCurrency)

    fun changeCurrencyData(search: String) {
        currencyList.value = currencies.value?.data?.values?.toList()

        val searchUpper = search.uppercase()
        val currentList = currencyList.value ?: emptyList()
        val filteredList = currentList.filter { it.code.startsWith(searchUpper) }
        currencyList.value = filteredList
    }


    fun getCurrencies() {
        getCurrenciesUseCase.execute(
            onSuccess = {
                isLoad.value = true
                currencies.value = it
                currencyList.value = it.data.values.toList()
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}