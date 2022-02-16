package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.plcoding.cryptocurrencyappyt.domain.model.CoinHistoricalPrice

data class CoinHistoricalPriceDto(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Long,
    val open: Double,
    val time_close: String,
    val time_open: String,
    val volume: Long
)

fun CoinHistoricalPriceDto.toCoinHistoricalPrice():CoinHistoricalPrice{
    return CoinHistoricalPrice(
        open = open
    )
}