package com.plcoding.cryptocurrencyappyt.data.remote

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinHistoricalPriceDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



interface CoinPaprikaApi {

    @GET("/v1/ticker")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
//?start={Current}&limit=366"
    @GET("/v1/coins/{coinId}/ohlcv/historical")
    suspend fun getCoinHistoricalPriceById(@Path("coinId") coinId:String, @Query("start")Current:String, @Query("limit") limit:Int= 366):List<CoinHistoricalPriceDto>
}