package com.fahimezv.githubsearch.data.network.service

import com.fahimezv.githubsearch.data.network.service.retrofit.SearchService
import com.fahimezv.githubsearch.data.network.service.retrofit.UserService


interface ServiceProvider {
    fun getSearchService(): SearchService
    fun getUserService(): UserService
}