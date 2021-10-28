package com.plcoding.cryptocurrencyappyt.di

import android.content.*
import com.plcoding.cryptocurrencyappyt.common.*
import com.plcoding.cryptocurrencyappyt.common.Constants.BASE_URL
import com.plcoding.cryptocurrencyappyt.data.remote.*
import com.plcoding.cryptocurrencyappyt.data.repository.*
import com.plcoding.cryptocurrencyappyt.domain.repository.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import okhttp3.*
import retrofit2.*
import retrofit2.converter.gson.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(directory = context.cacheDir, maxSize = (5 * 1024 * 1024).toLong()))
            .addInterceptor { chain ->
                val request = chain.request()
                if (hasNetwork(context) == true)
                // if there is internet get the cache that was stored 5 secs ago.
                // if cache is older than 5 secs, discard it.
                // max-age attr is responsible for this behave
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 5
                    ).build()
                else
                // if no internet, get the cache stored 7 days ago.
                // if cache is older that 7 days, discard it.
                // max-stale attr
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()

                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun providesCoinPaprikaApi(okHttpClient: OkHttpClient): CoinPaprikaApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(CoinPaprikaApi::class.java)


    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository =
        CoinRepositoryImpl(api)

}