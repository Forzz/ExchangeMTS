package com.forzz.exchangermts.presentation.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.forzz.exchangermts.R
import com.forzz.exchangermts.databinding.FragmentConverterBinding
import com.forzz.exchangermts.domain.models.Currency
import com.forzz.exchangermts.presentation.home_page.HomePageFragmentArgs
import com.forzz.exchangermts.presentation.home_page.HomePageViewModel
import java.text.DecimalFormat

class ConverterFragment : Fragment() {

    private lateinit var binding: FragmentConverterBinding
    private val viewModel: ConverterViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterBinding.inflate(inflater, container, false)

        val args: HomePageFragmentArgs by navArgs()
        val currency: Currency = args.selectedCurrency

        binding.etBaseCurrency.hint = "RUB"
        binding.etSelectedCurrency.hint = currency.code

        binding.etBaseCurrency.editText?.setText("1.0")
        binding.etSelectedCurrency.editText?.setText("${currency.value}")

        val descriptionText = "1 RUB = ${formatDouble(currency.value)} ${currency.code}"
        binding.tvDescription.text = descriptionText

        viewModel.init(currency.value.toDouble())

        viewModel.selectedCurrency.observe(viewLifecycleOwner) { selectedValue ->
            binding.etSelectedCurrency.editText?.setText(selectedValue.toString())
        }

        binding.etBaseCurrency.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputValue = s.toString().toDoubleOrNull() ?: 0.0
                viewModel.onBaseCurrencyChanged(inputValue)
            }

            override fun afterTextChanged(s: Editable?) {
                // No action
            }
        })

        return binding.root
    }

    fun formatDouble(value: Double): String {
        val decimalFormat = DecimalFormat("#.######")
        return decimalFormat.format(value)
    }
}

