package com.fahimezv.githubsearch.presentation.ui.screen.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fahimezv.githubsearch.presentation.ui.screen.search.sub.SearchAdapter
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseFragmentVMState
import com.fahimezv.githubsearch.presentation.extentions.TAG
import com.fahimezv.githubsearch.presentation.ui.common.RecyclerViewDecorations
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragmentVMState<SearchViewModel>() {

    //View Model
    override val viewModel: SearchViewModel by viewModel {
        parametersOf(requireContext().getString(R.string.anErrorOccurred))
    }

    //UI
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var loading: ProgressBar
    private lateinit var empty: TextView
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Bind View
        val view = inflater.inflate(R.layout.search_fragment, container, false)
       //Loading
        loading = view.findViewById(R.id.loading_progressbar)
       //Search
        searchEditText = view.findViewById(R.id.searchView)
        //Empty View
        empty = view.findViewById<TextView?>(R.id.empty_textView).apply {
            isVisible = false
        }
        //Recycler View
        recyclerView = view.findViewById<RecyclerView?>(R.id.searchList_recyclerView).apply {
            isVisible = false
            searchAdapter = SearchAdapter { user ->
                navigate(SearchFragmentDirections.actionSearchFragmentToUserInfoFragment(user.login))
            }.apply {

                //Ui State Handling
                addLoadStateListener { loadState ->
                    Log.d(TAG, loadState.toString())
                    if (loadState.refresh is LoadState.NotLoading && itemCount == 0) { // show empty list
                        onEmpty()
                    } else if (loadState.source.refresh is LoadState.Loading) { // Show loading spinner during initial load or refresh.
                        onLoading()
                    } else if (loadState.source.refresh is LoadState.NotLoading) { // Only show the list if refresh succeeds.
                        onData()

                    } else {
                        onNetworkError()
                    }

                }
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
        return view
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Observe Data
        viewModel.usersLiveData.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                searchAdapter.submitData(pagingData)
            }
        }

        //Search Handling
        Observable.create(ObservableOnSubscribe<CharSequence> { subscriber ->
            searchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(newText: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (newText != null) {
                        subscriber.onNext(newText)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        })
            .map { text -> text.trim() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinct()
            .subscribe { text ->
                Log.d(TAG, "subscriber: $text")
                viewModel.setTermSearch(text.toString())
                searchAdapter.refresh()
            }
    }

    override fun onLoading() {
        loading.isVisible = true
        empty.isVisible = false
        recyclerView.isVisible = false

    }

    override fun onData() {
        recyclerView.isVisible = true
        loading.isVisible = false
        empty.isVisible = false
    }

    override fun onEmpty() {
        recyclerView.isVisible = false
        loading.isVisible = false
        empty.isVisible = true
    }

    override fun onNetworkError() {
        recyclerView.isVisible = false
        loading.isVisible = false
    }



}