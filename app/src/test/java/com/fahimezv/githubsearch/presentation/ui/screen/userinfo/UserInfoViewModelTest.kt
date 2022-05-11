package com.fahimezv.githubsearch.presentation.ui.screen.userinfo

import androidx.lifecycle.Observer
import com.fahimezv.githubsearch.core.entity.Search
import com.fahimezv.githubsearch.data.network.repository.UserRepository
import com.fahimezv.githubsearch.presentation.ui.architecture.BaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import com.fahimezv.githubsearch.data.network.model.Result
import com.fahimezv.githubsearch.presentation.architecture.UiState

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserInfoViewModelTest : BaseTest() {

    @Mock
    private lateinit var userInfLiveData: Observer<Search.User>

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: UserInfoViewModel

    private val userItem = Search.User("", 1,
        "", "", "", "",
        "", "","", "",
        "", "", "",
        "", "", "",
        "", "", false, 0F,
        "", "", "", "", "",
        "", "", "", "",
        "", "", "", "", "")


    @Before
    fun setup() {
        testCoroutineRule.runBlockingTest {
            viewModel = Mockito.spy(
                UserInfoViewModel(
                    userName = "Not important ",
                    userRepository = userRepository
                )
            )
        }
        viewModel.getUserLiveData().observeForever(userInfLiveData)

    }

    @Test
    fun `when userRepository_userInfo() request's called then it has result  finally return postItem`() {
        testCoroutineRule.runBlockingTest {
            whenever(userRepository.userInfo(userName = "doesn't matter")).thenReturn(
                Result.Data(
                    userItem
                )
            )
            viewModel.requestUserInfo(userName = "doesn't matter")
            verify(userInfLiveData).onChanged(userItem)
        }
    }

    @Test
    fun `when userRepository_userInfo() response is Data then UiState must be Data`() {
        testCoroutineRule.runBlockingTest {
            whenever(userRepository.userInfo(userName = "doesn't matter")).thenReturn(
                Result.Data(
                    userItem
                )
            )
            viewModel.requestUserInfo(userName = "doesn't matter")
            verify(viewModel).uiState(eq(UiState.Data))
        }
    }

    @Test
    fun `when userRepository_userInfo()response is Data then UiState must be NetworkError`() {
        testCoroutineRule.runBlockingTest {
            whenever(userRepository.userInfo(userName = "doesn't matter")).thenReturn(
                Result.NetworkError(
                    Throwable("Not Important")
                )
            )
            viewModel.requestUserInfo(userName = "doesn't matter")
            verify(viewModel).uiState(eq(UiState.NetworkError))
        }

    }
}