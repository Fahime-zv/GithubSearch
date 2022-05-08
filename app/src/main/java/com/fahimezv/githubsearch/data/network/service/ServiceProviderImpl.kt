package com.fahimezv.githubsearch.data.network.service

import com.fahimezv.githubsearch.data.network.generator.NetworkServiceFactory
import com.fahimezv.githubsearch.data.network.service.retrofit.SearchService
import com.fahimezv.githubsearch.data.network.service.retrofit.UserService


class ServiceProviderImpl(private val serviceFactory: NetworkServiceFactory) : ServiceProvider {
    override fun getSearchService(): SearchService {
        return serviceFactory.create(SearchService::class.java)
    }

    override fun getUserService(): UserService {
        return serviceFactory.create(UserService::class.java)
    }
}