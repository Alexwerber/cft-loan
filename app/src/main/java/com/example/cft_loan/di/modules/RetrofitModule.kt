package com.example.cft_loan.di.modules

import com.example.cft_loan.data.remote.ApiService
import com.example.cft_loan.di.constants.Api.BASE_URL
import com.example.cft_loan.di.constants.Api.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().run {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        addInterceptor(interceptor)
        readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
}