package com.fahimezv.githubsearch.presentation

import com.fahimezv.githubsearch.presentation.ui.screen.search.SearchViewModel
import com.fahimezv.githubsearch.presentation.ui.screen.userinfo.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    //****************************************
    //              ViewModels               *
    //****************************************/
    viewModel {parameters->
        SearchViewModel(searchRepository = get(),networkErrorMsg = parameters.get())
    }

    viewModel {parameters->
        UserInfoViewModel(userName = parameters.get(),userRepository = get())
    }
}



