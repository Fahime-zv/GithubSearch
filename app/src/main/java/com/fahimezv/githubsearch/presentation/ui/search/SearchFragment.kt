package com.fahimezv.githubsearch.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fahimezv.githubsearch.presentation.ui.search.sub.SearchAdapter
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseFragmentVMState
import com.fahimezv.githubsearch.presentation.ui.common.RecyclerViewDecorations
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragmentVMState<SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel ()

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var loading: ProgressBar
    private lateinit var error: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading = view.findViewById(R.id.loading_progressbar)

        error = view.findViewById<TextView?>(R.id.error_textView).apply {
            isVisible = false
        }

        recyclerView = view.findViewById<RecyclerView?>(R.id.searchList_recyclerView).apply {

            isVisible = false

            searchAdapter = SearchAdapter {

            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = searchAdapter

            addItemDecoration(
                RecyclerViewDecorations.NoLastItemDividerDecorator(
                    requireContext(),
                    LinearLayout.VERTICAL
                )
            )
        }


        viewModel.getSearchListLiveData().observe(viewLifecycleOwner) { list ->
            searchAdapter.bind(list.items)
        }

    }

    override fun onLoading() {
        loading.isVisible = true
        error.isVisible = false

    }

    override fun onData() {
        recyclerView.isVisible = true
        loading.isVisible = false
        error.isVisible = false


    }

    override fun onNetworkError() {
        recyclerView.isVisible = false
        loading.isVisible = false
        error.isVisible = true


    }

}