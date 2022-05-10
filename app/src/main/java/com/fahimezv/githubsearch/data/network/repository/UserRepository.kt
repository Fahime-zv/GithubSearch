package com.fahimezv.githubsearch.data.network.repository

import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.model.Result
import com.fahimezv.githubsearch.data.network.service.ServiceProvider

class UserRepository(
    serviceProvider: ServiceProvider
) : BaseRepository() {

    private val service = serviceProvider.getUserService()

    suspend fun userInfo(userName: String): Result<Search.User> {
        return safeApiCall {
            service.userInfo(userName = userName)
        }
    }
}