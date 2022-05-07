package com.fahimezv.githubsearch.presentation.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.repository.SearchRepository
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModelState
import com.fahimezv.githubsearch.presentation.architecture.UiState
import com.fahimezv.githubsearch.data.network.model.Result
import com.fahimezv.githubsearch.presentation.extentions.TAG

class SearchViewModel(
    private val searchRepository: SearchRepository
) :
    BaseViewModelState() {

    private val searchLiveData = MutableLiveData<Search>()
    fun getSearchListLiveData(): LiveData<Search> = searchLiveData

    init {
        requestSearch()
    }

    @SuppressLint("VisibleForTests")
    fun requestSearch() {
        viewModelScope.launch {
            uiState(UiState.Loading)
            with(searchRepository.search("fahime")) {
                when (this) {
                    is Result.Data -> {
                        searchLiveData.postValue(this.model)
                        uiState(UiState.Data)
                        Log.d(this@SearchViewModel.TAG, "requestSearch: ${this.model}")
                    }
                    is Result.NetworkError -> {
                        uiState(UiState.NetworkError)
                    }
                }
            }

        }
    }
}