package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail


data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
