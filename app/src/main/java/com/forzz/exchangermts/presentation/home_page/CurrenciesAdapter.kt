package com.forzz.exchangermts.presentation.home_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.forzz.exchangermts.databinding.CurrencyItemBinding
import com.forzz.exchangermts.domain.models.Currencies
import com.forzz.exchangermts.domain.models.Currency
import java.text.DecimalFormat

class CurrenciesAdapter(
    private val navController: NavController
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currencies: MutableList<Currency> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater, parent, false)

        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CurrencyViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Currency = currencies[position]

    fun setData(list: List<Currency>) {
        currencies.clear()
        currencies.addAll(list)
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(currency: Currency) {
            binding.tvCurrencyName.text = currency.code
            val decimalFormat = DecimalFormat("#.######")
            binding.tvCurrencyValue.text = decimalFormat.format(currency.value)

            itemView.setOnClickListener {
                val action = HomePageFragmentDirections.actionHomePageFragmentToConverterFragment(currency)
                navController.navigate(action)
            }
        }
    }
}