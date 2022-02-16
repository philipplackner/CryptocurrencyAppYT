package com.plcoding.cryptocurrencyappyt.presentation.coin_list.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import com.plcoding.cryptocurrencyappyt.domain.model.Coin


@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick:(Coin)->Unit
) {
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(coin) }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
       Text(
           text = "${coin.rank}. ${coin.name} (${coin.symbol})",
           style = MaterialTheme.typography.body1,
           overflow = TextOverflow.Ellipsis
       )
       Text(
            text = coin.price.dropLast(7),
            color=if(coin.change.toDouble()>0.0) Color(154,200,0) else Color(200,0,0),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
       )
    }
}