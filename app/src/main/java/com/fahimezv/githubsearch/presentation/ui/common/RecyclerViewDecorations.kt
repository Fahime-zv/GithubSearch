package com.fahimezv.githubsearch.presentation.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewDecorations {
    class NoLastItemDividerDecorator(
        val context: Context,
        orientation: Int
    ) : DividerItemDecoration(context, orientation) {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val position = parent.getChildAdapterPosition(view)
            val last = parent.adapter?.itemCount ?: 0

            if (position == last - 1) {
                outRect.set(0, 0, 0, 0)
            }
        }

    }
}