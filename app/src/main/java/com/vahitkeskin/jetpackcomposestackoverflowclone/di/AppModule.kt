package com.vahitkeskin.jetpackcomposestackoverflowclone.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.google.gson.GsonBuilder
import com.vahitkeskin.jetpackcomposestackoverflowclone.BuildConfig
import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains.BASE_URL
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.FlipperNetworkObject
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @authot: Vahit Keskin
 * creared on 18.12.2022
 */
@dagger.Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client().build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun client(): OkHttpClient.Builder {
        return if (BuildConfig.DEBUG && FlipperNetworkObject.networkFlipperPlugin != null) {
            OkHttpClient.Builder()
                .addNetworkInterceptor(FlipperOkhttpInterceptor(FlipperNetworkObject.networkFlipperPlugin))
        } else {
            OkHttpClient.Builder()
        }
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}