package com.riad.pexelsdemoapp.data.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TokenAuthInterceptor(token: String) :
    Interceptor {
    private val credentials: String
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }

    init {
        credentials = token
    }
}