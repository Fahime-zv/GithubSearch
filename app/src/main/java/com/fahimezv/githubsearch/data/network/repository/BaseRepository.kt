package com.fahimezv.githubsearch.data.network.repository

import android.util.Log
import com.fahimezv.githubsearch.presentation.extentions.TAG
import com.fahimezv.githubsearch.core.entity.NetworkReceivedModel
import com.fahimezv.githubsearch.data.network.model.MappableDAO
import com.fahimezv.githubsearch.data.network.model.Result
abstract class BaseRepository {
    inline fun <reified M : NetworkReceivedModel, T : MappableDAO<M>> safeApiCall(apiCall: () -> T): Result<M> {
        // Calling the apiCall method that passed as a higher order function
        val result = try {
            val response = apiCall.invoke()
            Log.i(TAG, "safeApiCall: $response")
            Result.Data(response.map())
        } catch (e: Throwable) { // If response code is not 200 then an exception will be thrown
            Log.e(TAG, "${e.message}")
            Result.NetworkError(e)
        }
        return result
    }
}

