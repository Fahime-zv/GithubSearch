package com.fahimezv.githubsearch.presentation

import com.fahimezv.githubsearch.presentation.ui.search.SearchViewModel
import com.fahimezv.githubsearch.presentation.ui.userinfo.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    //****************************************
    //              ViewModels               *
    //****************************************/
    viewModel {
        SearchViewModel(searchRepository = get())
    }

    viewModel {parameters->
        UserInfoViewModel(userName = parameters.get(),userRepository = get())
    }
}



