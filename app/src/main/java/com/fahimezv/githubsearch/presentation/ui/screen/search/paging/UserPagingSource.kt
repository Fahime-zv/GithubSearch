package com.fahimezv.githubsearch.presentation.ui.screen.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.repository.SearchRepository
import com.fahimezv.githubsearch.data.network.model.Result

private const val TMDB_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 30

class UserPagingSource(
    private val term: String,
    private val searchRepository: SearchRepository
) : PagingSource<Int, Search.User>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search.User> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX


       with(searchRepository.search(term = term, pageIndex = pageIndex)) {
           return when (this) {
                is Result.Data -> {
                    val nextKey = if (this.model.totalCount > 0)
                        pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                    else null
                    LoadResult.Page(
                        data = this.model.items,
                        prevKey = null,
                        nextKey = nextKey
                    )
                }

                is Result.NetworkError -> {
                    LoadResult.Error(Throwable())
                }
            }


        }
    }


    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Search.User>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}