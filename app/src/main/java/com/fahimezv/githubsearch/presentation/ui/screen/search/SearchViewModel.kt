package com.fahimezv.githubsearch.presentation.ui.screen.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import kotlinx.coroutines.launch
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.repository.SearchRepository
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModelState
import com.fahimezv.githubsearch.presentation.ui.screen.search.paging.UserPagingSource

class SearchViewModel(
    private val searchRepository: SearchRepository,
) :
    BaseViewModelState() {


    private lateinit var pagingSource: UserPagingSource

    private  var term: String=""

    fun setTermSearch(term:String){
        this.term =term
    }

    val usersLiveData = Pager(PagingConfig(pageSize = 30, initialLoadSize = 1)) {
        pagingSource = UserPagingSource(term,searchRepository)
        pagingSource
    }.liveData.cachedIn(viewModelScope)

}