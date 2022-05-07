package com.fahimezv.githubsearch.presentation.architecture

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper


abstract class BaseFragmentVM<VM : BaseViewModel> : BaseFragment() {

        protected abstract val viewModel: VM

        @CallSuper
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

    }
}