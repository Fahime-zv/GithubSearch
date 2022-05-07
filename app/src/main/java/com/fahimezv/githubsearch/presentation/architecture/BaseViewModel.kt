package com.fahimezv.githubsearch.presentation.architecture

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections

abstract class BaseViewModel : ViewModel() {

    /**
     * Some convenience functions to have the navigation ability in viewModels directly.
     *
     * Examples:
     *
     * + Go to another fragment:
     *
     * navigate(ChooseLanguageFragmentDirections.actionChooseLanguageFragmentToHomeFragment())
     *
     * + Go to previous fragment:
     *
     * navigateUp()
     */

    private val navigationCommandsLiveData = SingleEventLiveData<NavigationCommand>()

    fun getNavigationCommandsLiveData(): SingleEventLiveData<NavigationCommand> =
        navigationCommandsLiveData

    @VisibleForTesting
    fun navigate(directions: NavDirections) {
        navigationCommandsLiveData.postValue(NavigationCommand.To(directions))
    }

    @VisibleForTesting
    fun navigateUp() {
        navigationCommandsLiveData.postValue(NavigationCommand.Back)
    }

    @VisibleForTesting
    fun navigateUpTo(destinationId: Int) {
        navigationCommandsLiveData.postValue(NavigationCommand.BackTo(destinationId))
    }

    /**
     * Some convenience functions to have the toasting ability in viewModels directly.*
     *
     * Examples:
     *
     * + Display a success toast:
     *
     * successToast("Password has been changed.")
     *
     * */

}
