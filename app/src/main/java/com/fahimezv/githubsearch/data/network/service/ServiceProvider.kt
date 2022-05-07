package com.fahimezv.githubsearch.data.network.service

import com.fahimezv.githubsearch.data.network.service.retrofit.SearchService


interface ServiceProvider {
    fun getSearchService(): SearchService
}