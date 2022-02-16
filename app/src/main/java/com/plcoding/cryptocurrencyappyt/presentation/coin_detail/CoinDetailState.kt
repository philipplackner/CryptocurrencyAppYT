package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.CoinHistoricalPrice

data class CoinDetailState(
    val isLoading:Boolean=false,
    val coin:CoinDetail ?=null,
    //val coinHistoricalPrice: List<CoinHistoricalPrice>?=null,
    val error:String=""
)
