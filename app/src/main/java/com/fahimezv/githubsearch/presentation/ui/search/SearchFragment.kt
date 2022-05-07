package com.fahimezv.githubsearch.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseFragmentVMState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragmentVMState<SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onLoading() {

    }

    override fun onData() {

    }

    override fun onNetworkError() {

    }

}