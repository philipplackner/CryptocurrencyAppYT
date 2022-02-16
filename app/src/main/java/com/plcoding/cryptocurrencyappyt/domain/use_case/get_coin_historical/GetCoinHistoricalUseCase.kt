package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coin_historical

import android.util.Log
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoinHistoricalPrice
import com.plcoding.cryptocurrencyappyt.domain.model.CoinHistoricalPrice
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinHistoricalUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(coinId:String , Current:String): Flow<Resource<List<CoinHistoricalPrice>>> =flow {
        try {
            emit(Resource.Loading<List<CoinHistoricalPrice>>())
            Log.d("pina","boi")
            val coinshistoricalprices=repository.getCoinHistoricalPriceById(coinId,Current).map { it.toCoinHistoricalPrice() }

            emit(Resource.Success<List<CoinHistoricalPrice>>(coinshistoricalprices))
        }
        catch (e:HttpException){
            emit(Resource.Error<List<CoinHistoricalPrice>>(e.localizedMessage?:"An unexpected error occured"))
        }
        catch (e:IOException){
            emit(Resource.Error<List<CoinHistoricalPrice>>("Couldn't reach server, most likely there's a problem with your internet connection"))
        }
    }
}