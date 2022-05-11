package com.fahimezv.githubsearch.presentation.ui.screen.search

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
import kotlinx.coroutines.Job

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val networkErrorMsg: String
) :
    BaseViewModelState() {

    private var job: Job? = null

    private val searchLiveData = MutableLiveData<Search>()
    fun getSearchListLiveData(): LiveData<Search> = searchLiveData

    @SuppressLint("VisibleForTests")
    fun requestSearch(term: String) {

        job?.cancel()

        job = viewModelScope.launch {
            uiState(UiState.Loading)
            with(searchRepository.search(term = term)) {
                when (this) {
                    is Result.Data -> {
                        if (this.model.totalCount == 0) {
                            uiState(UiState.Empty)

                        } else {
                            searchLiveData.postValue(this.model)
                            uiState(UiState.Data)
                            Log.d(this@SearchViewModel.TAG, "requestSearch: ${this.model}")
                        }

                    }
                    is Result.NetworkError -> {
                        uiState(UiState.NetworkError)
                        errorToast(networkErrorMsg)
                    }
                }
            }

        }

    }

}