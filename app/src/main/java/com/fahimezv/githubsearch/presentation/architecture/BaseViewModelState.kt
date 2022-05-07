package com.fahimezv.githubsearch.presentation.architecture

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModel
import com.fahimezv.githubsearch.presentation.architecture.UiState


abstract class BaseViewModelState : BaseViewModel() {

    /**
     * A convenience function to have a control on the UI state of a Fragment that extends
     * BaseFragmentVMState. uiState accept these values:
     * 1. Loading        : when an operation is operating
     * 2. Empty         : when operation is done but no data received
     * 3. Data        : when operation is done and data received
     * 4. NetworkError   : when a network error happens and normally a try again button shows to the user
     * 5. ApiError        : when operation is done with failure. It accepts a string to be shown in UI
     *
     * Example:
     *
     * + uiState(Loading)
     * + uiState(Empty)
     * + uiState(Data)
     * + uiState(NetworkError)
     * + uiState(ApiError("Something went wrong"))
     *
     * */

    // UiStates
    private val uiStateLiveData = MutableLiveData<UiState>()
    fun getUiStateLiveData(): LiveData<UiState> = uiStateLiveData

    @VisibleForTesting
    fun uiState(uiState: UiState) {
        uiStateLiveData.postValue(uiState)
    }
}