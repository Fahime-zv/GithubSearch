package com.fahimezv.githubsearch.presentation.ui.userinfo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.model.Result
import com.fahimezv.githubsearch.data.network.repository.UserRepository
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModelState
import com.fahimezv.githubsearch.presentation.architecture.UiState
import com.fahimezv.githubsearch.presentation.extentions.TAG
import kotlinx.coroutines.launch

class UserInfoViewModel(
      userName: String,
    private val userRepository: UserRepository
) : BaseViewModelState() {

    init {
        requestUserInfo(userName)
    }

    private val userLiveData = MutableLiveData<Search.User>()
    fun getUserLiveData(): LiveData<Search.User> = userLiveData

    @SuppressLint("VisibleForTests")
    fun requestUserInfo(userName: String) {

        viewModelScope.launch {
            uiState(UiState.Loading)
            with(userRepository.userInfo(userName = userName)) {
                when (this) {
                    is Result.Data -> {
                        userLiveData.postValue(this.model)
                        uiState(UiState.Data)
                        Log.d(
                            this@UserInfoViewModel.TAG,
                            "requestUser: ${this.model}"
                        )
                    }
                    is Result.NetworkError -> {
                        uiState(UiState.NetworkError)
                    }
                }
            }

        }

    }

}