package com.forzz.exchangermts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.forzz.exchangermts.domain.use_cases.currencies.GetCurrenciesUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}