package com.vahitkeskin.jetpackcomposestackoverflowclone.di

import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 7.01.2023
 */
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(
            Contains.HEADERNAMES_CONTENT_TYPE,
            Contains.HEADERNAMES_APPLICATION_JSON
        )
        return chain.proceed(requestBuilder.build())
    }
}