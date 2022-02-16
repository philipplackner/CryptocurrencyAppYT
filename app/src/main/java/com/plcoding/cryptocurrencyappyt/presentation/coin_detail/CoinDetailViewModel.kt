package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val getCoinUseCase: GetCoinUseCase, savedStateHandle: SavedStateHandle):ViewModel(){
    private val _stateCoinDetail = mutableStateOf(CoinDetailState())
    val stateCoinDetail : State<CoinDetailState> = _stateCoinDetail


    init{
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId->
            getCoin(coinId)

        }

    }

    private fun getCoin(coinId:String){
        getCoinUseCase(coinId).onEach { result->
            when(result){
                is Resource.Success->{
                    _stateCoinDetail.value= CoinDetailState(coin=result.data)
                }
                is Resource.Error->{
                    _stateCoinDetail.value= CoinDetailState(error=result.message?:"Unexpected error")
                }
                is Resource.Loading->{
                    _stateCoinDetail.value= CoinDetailState(isLoading=true)
                }
            }
        }.launchIn(viewModelScope)


    }
}