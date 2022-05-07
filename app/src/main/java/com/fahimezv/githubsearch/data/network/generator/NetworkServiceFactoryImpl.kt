package com.fahimezv.githubsearch.data.network.generator

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter

class NetworkServiceFactoryImpl(
    private val converterFactory: Converter.Factory,
    logLevel: HttpLoggingInterceptor.Level,
    private  val environment: NetworkEnvironment,
    private val networkHeadersGenerator: NetworkHeaderGenerator
) : NetworkServiceFactory(converterFactory, logLevel, environment) {
    override fun interceptors(): List<NetworkInterceptor> {
        return arrayListOf(
            getNetworkRequestInterceptor()
        )
    }

    override fun serviceDependentInterceptors(): List<NetworkInterceptor> {
        return arrayListOf(
            getNetworkRequestInterceptor()
        )
    }

    private fun getNetworkRequestInterceptor(): NetworkInterceptor {
        return NetworkRequestInterceptor(networkHeadersGenerator)
    }


    // this code is Example if you need add service Interceptor
//    private fun getNetworkResponseInterceptor(): NetworkInterceptor {
//        val deviceAuthService = getRetrofitBuilder(
//            getHttpClientBuilder().build(),
//            converterFactory,
//            environment.baseUrl
//        ).build().create(DeviceAuthService::class.java)
//        return NetworkResponseInterceptor(networkHeadersGenerator, networkMutexAuth, modelUtils, metaCenter)
//    }
}