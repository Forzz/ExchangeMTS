package com.forzz.exchangermts.presentation.home_page

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.forzz.exchangermts.R
import com.forzz.exchangermts.databinding.FragmentHomePageBinding
import com.forzz.exchangermts.domain.models.Currency

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private var adapter: CurrenciesAdapter? = null

    private val viewModel: HomePageViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController()

        adapter = CurrenciesAdapter(navController)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        binding.rvCurrencies.layoutManager = GridLayoutManager(context, 2)
        binding.rvCurrencies.adapter = adapter

        binding.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // No Action
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val search = p0.toString()
                viewModel.changeCurrencyData(search)
            }

            override fun afterTextChanged(p0: Editable?) {
                // No Action
            }

        })

        viewModel.saveData("", "RUB")
        viewModel.getCurrencies()

        viewModel.currencyList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }

        return binding.root
    }

    private fun initRecyclerView(currencies: List<Currency>) {
        adapter?.setData(currencies)
    }
}