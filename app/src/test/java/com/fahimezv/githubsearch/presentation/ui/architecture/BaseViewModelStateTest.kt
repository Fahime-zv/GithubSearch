package com.fahimezv.githubsearch.presentation.ui.architecture

import androidx.lifecycle.Observer
import com.fahimezv.githubsearch.presentation.architecture.BaseViewModelState
import com.fahimezv.githubsearch.presentation.architecture.UiState
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
class BaseViewModelStateTest : BaseTest() {

    @Mock
    private lateinit var uiStateLiveData: Observer<UiState>

    class FakeModel : BaseViewModelState()

    private val viewModel = FakeModel()

    @Before
    fun setup() {
        viewModel.getUiStateLiveData().observeForever(uiStateLiveData)
    }

    @Test
    fun `when uiState() called then UiState posted to uiStateLiveData`() {
        val uiState = UiState.Data
        viewModel.uiState(uiState)
        verify(uiStateLiveData).onChanged(eq(uiState))
    }

}