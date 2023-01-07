package com.vahitkeskin.jetpackcomposestackoverflowclone.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.vahitkeskin.jetpackcomposestackoverflowclone.BuildConfig
import com.vahitkeskin.jetpackcomposestackoverflowclone.api.Service
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.FlipperNetworkObject
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        apiUrl: String
    ): Retrofit {
        return Retrofit.Builder().baseUrl(apiUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
    }

    @Provides
    fun provideStackoverflowBaseUrl(): String = Contains.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        chuckInterceptor: ChuckerInterceptor,
        flipperOkhttpInterceptor: FlipperOkhttpInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(flipperOkhttpInterceptor)
            .addInterceptor(
                if (BuildConfig.DEBUG)
                    chuckInterceptor
                else
                    authInterceptor
            )
            .addInterceptor(httpLoggingInterceptor).build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
        chuckerCollector: ChuckerCollector
    ): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).collector(chuckerCollector)
            .maxContentLength(Contains.CONTENT_LENGTH)
            .redactHeaders(
                Contains.HEADERNAMES_CONTENT_TYPE, Contains.HEADERNAMES_APPLICATION_JSON
            )
            .alwaysReadResponseBody(true)
            .build()

    @Singleton
    @Provides
    fun provideChuckerCollector(
        @ApplicationContext context: Context
    ): ChuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    @Provides
    @Singleton
    fun client(): FlipperOkhttpInterceptor {
        return if (BuildConfig.DEBUG && FlipperNetworkObject.networkFlipperPlugin != null) {
            FlipperOkhttpInterceptor(FlipperNetworkObject.networkFlipperPlugin)
        } else {
            FlipperOkhttpInterceptor(null)
        }
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}