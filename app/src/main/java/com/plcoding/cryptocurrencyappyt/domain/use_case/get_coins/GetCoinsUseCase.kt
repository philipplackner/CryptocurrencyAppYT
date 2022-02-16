package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> =flow {
          try {
              emit(Resource.Loading<List<Coin>>())
              val coins=repository.getCoins().map { it.toCoin() }
              emit(Resource.Success<List<Coin>>(coins))
          }
          catch (e:HttpException){
              emit(Resource.Error<List<Coin>>(e.localizedMessage?:"An unexpected error occured"))
          }
          catch (e:IOException){
              emit(Resource.Error<List<Coin>>("Couldn't reach server, most likely there's a problem with your internet connection"))
          }
    }
}