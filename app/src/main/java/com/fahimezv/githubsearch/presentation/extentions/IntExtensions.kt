package com.fahimezv.githubsearch.presentation.extentions

import android.content.res.Resources


/**
 * A convenience Int property extension to convert intended size from DpScale to Pixels
 *
 * Example:
 * ```
 * view.setPadding(8.dpToPx)
 * ```
 * @receiver intended size in DpScale
 * @return converted value from Dp scale to Px
 * */
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()