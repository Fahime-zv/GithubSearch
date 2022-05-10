package com.fahimezv.githubsearch.data

import com.fahimezv.githubsearch.BuildConfig
import com.fahimezv.githubsearch.data.network.generator.*
import com.fahimezv.githubsearch.data.network.repository.SearchRepository
import com.fahimezv.githubsearch.data.network.repository.UserRepository
import com.fahimezv.githubsearch.data.network.service.ServiceProvider
import com.fahimezv.githubsearch.data.network.service.ServiceProviderImpl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {


    //***************************************
    //               Network                *
    //***************************************

    // ------------- Generators -------------
    single {
        //You can crete custom GsonConverterFactory here
        val converterFactory: Converter.Factory = GsonConverterFactory.create()
        converterFactory
    }
    factory {
        if (BuildConfig.DEBUG) {    // development build
            HttpLoggingInterceptor.Level.BODY
        } else {    // production build
            HttpLoggingInterceptor.Level.BASIC
        }
    }

    single {
        val networkEnvironment: NetworkEnvironment = NetworkEnvironmentImpl()
        networkEnvironment
    }

    factory {
        val networkHeadersGenerator: NetworkHeaderGenerator = NetworkHeaderGeneratorImpl()
        networkHeadersGenerator
    }


    single {
        val networkServiceFactory: NetworkServiceFactory = NetworkServiceFactoryImpl(
            converterFactory = get(),
            logLevel = get(),
            environment = get(),
            networkHeadersGenerator = get()
        )
        networkServiceFactory
    }

    factory {
        val serviceProvider: ServiceProvider = ServiceProviderImpl(
            serviceFactory = get()
        )
        serviceProvider
    }

    factory {
        NetworkEnvironmentImpl()
    }
    // ------------- Repositories -------------
    factory {
        SearchRepository(serviceProvider = get())
    }

    factory {
        UserRepository(serviceProvider = get())
    }
}

