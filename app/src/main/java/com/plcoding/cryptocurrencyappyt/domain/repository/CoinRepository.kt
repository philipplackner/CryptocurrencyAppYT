package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinHistoricalPriceDto

interface CoinRepository {
    suspend fun getCoins():List<CoinDto>
    suspend fun getCoinById(coinId:String): CoinDetailDto
    suspend fun getCoinHistoricalPriceById(coinId:String, Current:String): List<CoinHistoricalPriceDto>
}