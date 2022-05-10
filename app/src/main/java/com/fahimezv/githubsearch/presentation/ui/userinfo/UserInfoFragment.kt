package com.fahimezv.githubsearch.presentation.ui.userinfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.architecture.BaseFragmentVMState
import com.fahimezv.githubsearch.presentation.ui.util.ImageLoaderUtils
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserInfoFragment : BaseFragmentVMState<UserInfoViewModel>() {

    override val viewModel: UserInfoViewModel by inject{
        parametersOf(args.userName)
    }

    private lateinit var avatarImageView: AppCompatImageView
    private lateinit var infoTextView: AppCompatTextView
    private lateinit var companyTextView: AppCompatTextView
    private lateinit var addressTextView: AppCompatTextView
    private lateinit var loading: ProgressBar


    private val args:UserInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        avatarImageView = view.findViewById(R.id.avatar_imageView)
        infoTextView = view.findViewById(R.id.info_TextView)
        companyTextView = view.findViewById(R.id.company_TextView)
        addressTextView = view.findViewById(R.id.address_TextView)
        loading = view.findViewById(R.id.loading_progressbar)

        viewModel.getUserLiveData().observe(viewLifecycleOwner) {user->
            ImageLoaderUtils.with(requireContext()).placeholder(R.drawable.noimage)
                .load(user.avatarUrl)
                .into(avatarImageView)
            infoTextView.text =
                " Name: ${user.name ?: "no name"} | Twitter: ${user.twitterUsername ?:"no twitter name"} | Location: ${user.location ?:"no Location"}"
            companyTextView.text ="Company name:${user.company ?: "no Company"}"

            addressTextView.text=" Url: ${user.url}"
        }

    }

    override fun onLoading(){
        loading.isVisible=true

    }


    override fun onData() {
        loading.isVisible=false

    }

    override fun onNetworkError() {
        loading.isVisible=false

    }


}