package com.fahimezv.githubsearch.presentation.architecture

import android.os.Bundle
import android.service.controls.ControlsProviderService
import android.util.Log
import android.view.View
import androidx.annotation.CallSuper
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.fahimezv.githubsearch.presentation.ui.util.ToastManagerUtils


/**
 * BaseFragmentVM, where all navigation commands get observed.
 * You should extend this fragment when you have a Fragment with a ViewModel without any operations
 * that changes the [UiState].
 * If you need to change the [UiState] you should extend the [BaseViewModelState]
 * */
abstract class BaseFragmentVM<VM : BaseViewModel> : BaseFragment() {

    protected abstract val viewModel: VM
    protected lateinit var toastManager: ToastManagerUtils

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe NavigationCommands
        viewModel.getNavigationCommandsLiveData()
            .observe(viewLifecycleOwner) { navigationCommand ->
                when (navigationCommand) {
                    is NavigationCommand.To -> navigate(navigationCommand.directions)
                    NavigationCommand.Back -> navigateUp()
                    // This Command didn't implemented in BaseViewModel due to problems it creates
                    // in writing tests for VMs
                    is NavigationCommand.BackTo -> findNavController().popBackStack(
                        navigationCommand.destinationId,
                        false
                    )
                }
            }
        // Observe ToastCommands
        toastManager = ToastManagerUtils(requireActivity())
        viewModel.getToastCommandsLiveData().observe(viewLifecycleOwner) {
            Log.d(ControlsProviderService.TAG, it.msg)
            when (it.type) {
                ToastCommand.Type.Success -> successToast(it.msg)
                ToastCommand.Type.Error -> errorToast(it.msg)
                ToastCommand.Type.Warning -> warningToast(it.msg)
                ToastCommand.Type.Info -> infoToast(it.msg)
            }
        }
    }

    // Toast Functions
    protected fun successToast(msg: String) {
        toastManager.success(msg)
    }

    protected fun errorToast(msg: String) {
        toastManager.error(msg)
    }

    protected fun warningToast(msg: String) {
        toastManager.warning(msg)
    }

    protected fun infoToast(msg: String) {
        toastManager.info(msg)
    }
}