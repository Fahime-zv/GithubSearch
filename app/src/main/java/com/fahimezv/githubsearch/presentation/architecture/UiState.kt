package com.fahimezv.githubsearch.presentation.architecture

sealed class UiState {
    object Loading : UiState()
    object Data : UiState()
    object NetworkError : UiState()

    override fun toString(): String {
        return when (this) {
            Data -> "Data"
            Loading -> "Loading"
            NetworkError -> "NetworkError"
        }
    }
}