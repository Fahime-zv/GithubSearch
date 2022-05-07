package com.fahimezv.githubsearch.data.network.generator

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

abstract class NetworkServiceFactory(
    private val converterFactory: Converter.Factory,
    private val logLevel: HttpLoggingInterceptor.Level,
    private val networkEnvironment: NetworkEnvironment
) {

    // General Interceptors (Which doesn't dependent on services)
    abstract fun interceptors(): List<NetworkInterceptor>

    // Interceptors which needs a service
    abstract fun serviceDependentInterceptors(): List<NetworkInterceptor>

    private fun getHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        // Build OkHttpClient
        builder.followRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        // Add Logging interceptor
        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = logLevel
            }
        )
        // Add Network Request Interceptor
        interceptors().forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }
        return builder
    }

    private fun getRetrofitBuilder(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        baseUrl: String
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
    }

    fun <T> create(serviceType: Class<T>): T {
        val okHttpClientBuilder = getHttpClientBuilder()
        /* We add this interceptors in create() function because they need a default okHttpClientBuilder
        * and if we added them in getHttpClientBuilder() function a StackOverflow Exception will be thrown */
        serviceDependentInterceptors().forEach { interceptor ->
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        val retrofit = getRetrofitBuilder(
            okHttpClientBuilder.build(),
            converterFactory,
            networkEnvironment.baseUrl
        ).build()
        return retrofit.create(serviceType)
    }


}