package com.fahimezv.githubsearch.presentation.ui.architecture

import androidx.lifecycle.Observer
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModel
import com.fahimezv.githubsearch.presentation.architecture.NavigationCommand
import com.fahimezv.githubsearch.presentation.architecture.ToastCommand
import com.fahimezv.githubsearch.presentation.ui.screen.search.SearchFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BaseViewModelTest : BaseTest() {

    @Mock
    private lateinit var navigationCommandsLiveData: Observer<NavigationCommand>

    @Mock
    private lateinit var toastLiveData: Observer<ToastCommand>

    class FakeModel : BaseViewModel()

    private val viewModel = FakeModel()

    @Before
    fun setup() {
        viewModel.getNavigationCommandsLiveData().observeForever(navigationCommandsLiveData)
        viewModel.getToastCommandsLiveData().observeForever(toastLiveData)
    }

    @Test
    fun `when navigate() called then direction posted to navigationCommandsLiveData`() {
        //direction docent matter only for test
        val direction = SearchFragmentDirections.actionSearchFragmentToUserInfoFragment("not matter")
        viewModel.navigate(direction)
        verify(navigationCommandsLiveData).onChanged(eq(NavigationCommand.To(direction)))
    }

    @Test
    fun `when navigateUp() called then Back direction posted to navigationCommandsLiveData`() {
        viewModel.navigateUp()
        verify(navigationCommandsLiveData).onChanged(eq(NavigationCommand.Back))
    }

    @Test
    fun `when navigateUpTo() called then back to destination direction posted to navigationCommandsLiveData`() {
        val directionWithId = R.id.searchFragment
        viewModel.navigateUpTo(directionWithId)
        verify(navigationCommandsLiveData).onChanged(eq(NavigationCommand.BackTo(directionWithId)))
    }

    @Test
    fun `when successToast() called then Success type posted to toastLiveData`() {
        val message = "doesn't Important"
        viewModel.successToast(message)
        verify(toastLiveData).onChanged(eq(ToastCommand(message, ToastCommand.Type.Success)))
    }


    @Test
    fun `when errorToast() called then Error type posted to toastLiveData`() {
        val message = "doesn't Important"
        viewModel.errorToast(message)
        verify(toastLiveData).onChanged(eq(ToastCommand(message, ToastCommand.Type.Error)))
    }

    @Test
    fun `when warningToast() called then Warning type posted to toastLiveData`() {
        val message = "doesn't Important"
        viewModel.warningToast(message)
        verify(toastLiveData).onChanged(eq(ToastCommand(message, ToastCommand.Type.Warning)))
    }

    @Test
    fun `when infoToast() called then Info type posted to toastLiveData`() {
        val message = "doesn't Important"
        viewModel.infoToast(message)
        verify(toastLiveData).onChanged(eq(ToastCommand(message, ToastCommand.Type.Info)))
    }

}