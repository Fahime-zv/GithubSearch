package com.fahimezv.githubsearch.presentation.ui.util


import android.app.Activity
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.component.AlertBuilder
import com.fahimezv.githubsearch.presentation.extentions.screenWidth

class ToastManagerUtils(private val activity: Activity) {

    fun success(msg: String) {
        AlertBuilder.Builder(activity, activity.screenWidth)
            .message(msg)
            .backgroundColor(activity.getColor(R.color.white))
            .lineColor(activity.getColor(R.color.success))
            .iconColor(activity.getColor(R.color.success))
            .textColor(activity.getColor(R.color.black))
            .build()
            .show()
    }

    fun error(msg: String) {
        AlertBuilder.Builder(activity, activity.screenWidth)
            .message(msg)
            .backgroundColor(activity.getColor(R.color.white))
            .lineColor(activity.getColor(R.color.error))
            .iconColor(activity.getColor(R.color.error))
            .textColor(activity.getColor(R.color.black))
            .build()
            .show()
    }

    fun warning(msg: String) {
        AlertBuilder.Builder(activity, activity.screenWidth)
            .message(msg)
            .backgroundColor(activity.getColor(R.color.white))
            .lineColor(activity.getColor(R.color.warning))
            .iconColor(activity.getColor(R.color.warning))
            .textColor(activity.getColor(R.color.black))
            .build()
            .show()
    }

    fun info(msg: String) {
        AlertBuilder.Builder(activity, activity.screenWidth)
            .message(msg)
            .backgroundColor(activity.getColor(R.color.white))
            .lineColor(activity.getColor(R.color.info))
            .iconColor(activity.getColor(R.color.info))
            .textColor(activity.getColor(R.color.black))
            .build()
            .show()
    }
}