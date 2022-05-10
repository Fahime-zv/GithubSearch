package com.fahimezv.githubsearch.presentation.architecture


data class ToastCommand(val msg: String, val type: Type) {
    sealed class Type {
        object Success : Type()
        object Error : Type()
        object Warning : Type()
        object Info : Type()
    }
}