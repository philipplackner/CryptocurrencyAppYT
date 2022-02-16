package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import com.plcoding.cryptocurrencyappyt.domain.model.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.model.CoinHistoricalPrice

data class CoinHistoricalPriceState(
    val isLoading:Boolean=false,
    val coinHistoricalPrice:List<CoinHistoricalPrice> ?=null,
    val error:String=""
)
