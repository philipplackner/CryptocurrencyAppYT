package com.plcoding.cryptocurrencyappyt.presentation.coin_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(val getCoinUseCase: GetCoinUseCase) :
    ViewModel() {

    private val _state: MutableLiveData<CoinDetailState> = MutableLiveData(CoinDetailState())
    val state: LiveData<CoinDetailState> = _state

        fun getCoin(coinId: String) {
            getCoinUseCase(coinId).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = CoinDetailState(coin = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                    }
                    is Resource.Loading -> {
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }

}