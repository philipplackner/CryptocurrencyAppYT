package com.plcoding.cryptocurrencyappyt.domain.model

data class Coin(
    val id: String,
    val price:String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val change: String
)
