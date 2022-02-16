package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.plcoding.cryptocurrencyappyt.domain.model.Coin


data class CoinDto(
    val circulating_supply: String,
    val id: String,
    val last_updated: String,
    val market_cap_usd: String,
    val max_supply: String,
    val name: String,
    val percent_change_1h: String,
    val percent_change_24h: String,
    val percent_change_7d: String,
    val price_btc: String,
    val price_usd: String,
    val rank: String,
    val symbol: String,
    val total_supply: String,
    val volume_24h_usd: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name=name,
        symbol = symbol,
        rank = rank.toInt(),
        price = price_usd,
        change = percent_change_24h

    )
}