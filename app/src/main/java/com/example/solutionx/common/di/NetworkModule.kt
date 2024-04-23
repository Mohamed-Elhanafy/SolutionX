package com.example.solutionx.common.di

import am.leon.utilities.android.helpers.logging.LoggerFactory
import android.content.Context
import androidx.startup.Initializer
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.solutionx.common.data.constants.Constants.BASE_URL
import com.example.solutionx.common.data.repository.local.DataStoreStorage
import com.example.solutionx.common.data.repository.remote.RetrofitApi
import com.example.solutionx.common.data.repository.remote.RetrofitRestApiProvider
import com.example.solutionx.common.domain.repository.local.KeyValueStorage
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideNetworkProvider(retrofitApi: RetrofitApi): IRestApiNetworkProvider {
        return RetrofitRestApiProvider(retrofitApi)
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): RetrofitApi =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(RetrofitApi::class.java)


    @Provides
    fun provideDataStoreStorage(@ApplicationContext context: Context): KeyValueStorage {
        return DataStoreStorage(context)
    }

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }


}


