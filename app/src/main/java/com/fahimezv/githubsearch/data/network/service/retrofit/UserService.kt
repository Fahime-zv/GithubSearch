package com.fahimezv.githubsearch.data.network.service.retrofit

import com.fahimezv.githubsearch.data.network.model.dao.SearchDAO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users/{username}")
    suspend fun userInfo(
        @Path("username") userName: String
    ): SearchDAO.UserDAO
}