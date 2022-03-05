package com.softaai.upstoxholding.di.module

import com.softaai.upstoxholding.data.remote.HoldingsApiService
import com.softaai.upstoxholding.data.remote.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class HoldingsApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder().addInterceptor(RequestInterceptor()).build()


    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): HoldingsApiService = Retrofit.Builder()
        .baseUrl(HoldingsApiService.API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .client(okHttpClient)
        .build()
        .create(HoldingsApiService::class.java)

}
