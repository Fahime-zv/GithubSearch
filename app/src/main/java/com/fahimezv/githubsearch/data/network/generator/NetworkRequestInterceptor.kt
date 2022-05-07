package com.fahimezv.githubsearch.data.network.generator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkRequestInterceptor(
    private val headersGenerator: NetworkHeaderGenerator,
) : NetworkInterceptor {
    // This class  added for add header for all request
    @Throws(IOException::class)
    @Synchronized
    override fun intercept(chain: Interceptor.Chain): Response {
        var newRequest = chain.request()
        val headers = headersGenerator.getHeaders()
        newRequest = addHeadersToRequest(newRequest, headers)
        return chain.proceed(newRequest)
    }

    private fun addHeadersToRequest(
        request: Request,
        networkHeaders: List<NetworkRequestHeader>
    ): Request {
        val requestBuilder = request.newBuilder()
        networkHeaders.forEach {
            requestBuilder.addHeader(it.key, it.value)
        }
        return requestBuilder.build()
    }

}