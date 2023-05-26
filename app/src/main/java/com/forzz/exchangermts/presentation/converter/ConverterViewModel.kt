package com.forzz.exchangermts.presentation.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor() : ViewModel() {

    private val _baseCurrency = MutableLiveData<Double>()
    private val _selectedCurrency = MutableLiveData<Double>()
    private val conversionRate = MutableLiveData<Double>()


    val selectedCurrency: LiveData<Double> = _selectedCurrency

    fun init(value: Double) {
        conversionRate.value = value
    }

    fun onBaseCurrencyChanged(value: Double) {
        _baseCurrency.value = value
        updateSelectedCurrency()
    }

    private fun updateSelectedCurrency() {
        val baseValue = _baseCurrency.value ?: 0.0
        val selectedValue = calculateSelectedCurrency(baseValue)
        _selectedCurrency.value = selectedValue
    }

    private fun calculateSelectedCurrency(baseValue: Double): Double {
        return baseValue * conversionRate.value!!
    }
}

