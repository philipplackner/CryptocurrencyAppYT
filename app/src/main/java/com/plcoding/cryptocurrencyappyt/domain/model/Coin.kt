package com.plcoding.cryptocurrencyappyt.domain.model

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
