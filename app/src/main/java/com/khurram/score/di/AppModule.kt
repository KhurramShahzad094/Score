package com.khurram.score.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khurram.score.network.APIsInterface
import com.khurram.score.repository.NetworkRepository
import com.khurram.score.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    fun providesNetworkAPI(retrofit: Retrofit.Builder): APIsInterface {
        return retrofit.build().create(APIsInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkRepository(apIsInterface: APIsInterface): NetworkRepository {
        return NetworkRepository(apIsInterface)
    }
}