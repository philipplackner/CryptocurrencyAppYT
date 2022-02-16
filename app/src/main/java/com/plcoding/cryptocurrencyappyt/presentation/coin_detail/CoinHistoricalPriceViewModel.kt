package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin_historical.GetCoinHistoricalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CoinHistoricalPriceViewModel @Inject constructor(private val getCoinHistoricalUseCase: GetCoinHistoricalUseCase,savedStateHandle: SavedStateHandle): ViewModel(){
    private val _stateCoinHistorical = mutableStateOf(CoinHistoricalPriceState())
    val stateCoinHistorical : State<CoinHistoricalPriceState> = _stateCoinHistorical


    val currentDateTime = LocalDateTime.now()
    val Current=currentDateTime.minusYears(1).format(DateTimeFormatter.ISO_DATE)

    init {
       savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoinHistoricalPrice(coinId, Current)

        }
    }

    fun getCoinHistoricalPrice(coinId: String, Current: String) {
        getCoinHistoricalUseCase(coinId, Current).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateCoinHistorical.value = CoinHistoricalPriceState(
                        coinHistoricalPrice = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _stateCoinHistorical.value =
                        CoinHistoricalPriceState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _stateCoinHistorical.value = CoinHistoricalPriceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}


