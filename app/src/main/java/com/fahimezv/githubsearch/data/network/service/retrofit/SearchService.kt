package com.fahimezv.githubsearch.data.network.service.retrofit

import com.fahimezv.githubsearch.data.network.model.dao.SearchDAO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/users")
    suspend fun search(
        @Query("q") term: String
    ): SearchDAO
}