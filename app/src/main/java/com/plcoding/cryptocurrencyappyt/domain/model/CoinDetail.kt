package com.plcoding.cryptocurrencyappyt.domain.model

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.Tag
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.data.remote.dto.Whitepaper

data class CoinDetail(
    val coinId : String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>,
)

fun CoinDetailDto.toCoinDetail(): CoinDetail{
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive =isActive,
        tags = tags.map{ it.name},
        team = team
    )
}