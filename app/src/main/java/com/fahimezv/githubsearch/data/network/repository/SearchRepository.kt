package com.fahimezv.githubsearch.data.network.repository

import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.model.Result
import com.fahimezv.githubsearch.data.network.service.ServiceProvider

class SearchRepository(
    serviceProvider: ServiceProvider
) : BaseRepository() {

    private val service = serviceProvider.getSearchService()

    suspend fun search(term: String,pageIndex:Int? = null): Result<Search> {
        return safeApiCall {
            service.search(term = term,page = pageIndex)
        }
    }
}