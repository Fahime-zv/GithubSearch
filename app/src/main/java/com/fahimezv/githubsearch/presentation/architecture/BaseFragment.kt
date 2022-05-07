package com.fahimezv.githubsearch.presentation.architecture

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * BaseFragment, where all default view settings are applied here.
 * You should extend this fragment when you don't have a ViewModel for your fragment.
 * */
abstract class BaseFragment : Fragment() {

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun navigateUp() {
        findNavController().popBackStack()
    }

    protected fun navigateUpTo(destinationId: Int) {
        findNavController().popBackStack(destinationId, false)
    }

}