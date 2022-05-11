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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fahimezv.githubsearch.presentation.ui.screen.search.sub.SearchAdapter
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseFragmentVMState
import com.fahimezv.githubsearch.presentation.extentions.TAG
import com.fahimezv.githubsearch.presentation.ui.common.RecyclerViewDecorations
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragmentVMState<SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModel {
        parametersOf(requireContext().getString(R.string.anErrorOccurred))
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var loading: ProgressBar
    private lateinit var empty: TextView
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.search_fragment, container, false)
        loading = view.findViewById(R.id.loading_progressbar)
        searchEditText = view.findViewById(R.id.searchView)

        empty = view.findViewById<TextView?>(R.id.empty_textView).apply {
            isVisible = false
        }

        recyclerView = view.findViewById<RecyclerView?>(R.id.searchList_recyclerView).apply {

            isVisible = false
            searchAdapter = SearchAdapter { user ->
                navigate(SearchFragmentDirections.actionSearchFragmentToUserInfoFragment(user.login))
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

        viewModel.getSearchListLiveData().observe(viewLifecycleOwner) { list ->
            searchAdapter.bind(list.items)
        }

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
            .filter { text -> text.isNotBlank() }
            .subscribe { text ->
                Log.d(TAG, "subscriber: $text")
                viewModel.requestSearch(term = text.toString())
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