package com.fahimezv.githubsearch.presentation.ui.architecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fahimezv.githubsearch.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
}