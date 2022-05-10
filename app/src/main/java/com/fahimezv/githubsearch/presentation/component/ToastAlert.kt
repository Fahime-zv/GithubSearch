package com.fahimezv.githubsearch.presentation.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.fahimezv.githubsearch.R
import com.fahimezv.githubsearch.presentation.extentions.dpToPx
import com.fahimezv.githubsearch.presentation.ui.LayoutPRM
import com.fahimezv.githubsearch.presentation.ui.margin

class AlertBuilder private constructor(context: Context, builder: Builder) {
    // TODO: Use SnackBar instead due to deprecation of toast.SetView()!
    private val mToast: Toast

    init {
        mToast = Toast(context).apply {
            view = TopAlertView(
                context = context,
                message = builder.message.toString(),
                textColor = builder.textColor,
                iconRes = builder.iconRes,
                iconColor = builder.iconColor,
                backgroundColor = builder.backgroundColor,
                lineColor = builder.lineColor,
                font = builder.typeface,
                sizeWidth = builder.screenWidth
            )
            duration = builder.duration
            setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        }
    }

    fun show() {
        mToast.show()
    }

    class Builder(private val context: Context, val screenWidth: Int) {

        var message: CharSequence = ""
            private set
        var lineColor = context.getColor(R.color.white)
            private set
        var textColor = context.getColor(R.color.black)
            private set
        var iconColor = context.getColor(R.color.black)
            private set
        var backgroundColor = context.getColor(R.color.black)
            private set
        var typeface: Typeface = Typeface.DEFAULT
            private set
        var iconRes: String? = null
            private set
        var duration = 1
            private set

        fun message(message: CharSequence) = apply {
            this.message = message
        }

        fun lineColor(color: Int) = apply {
            this.lineColor = color
        }

        fun textColor(color: Int) = apply {
            this.textColor = color
        }

        fun iconColor(color: Int) = apply {
            this.iconColor = color
        }

        fun icon(iconRes: String?) = apply {
            this.iconRes = iconRes
        }

        fun backgroundColor(color: Int) = apply {
            this.backgroundColor = color
        }

        fun typeFace(font: Typeface) = apply {
            this.typeface = font
        }

        fun setDuration(duration: Int) = apply {
            this.duration = duration
        }

        fun build(): AlertBuilder {
            return AlertBuilder(context, this)
        }


    }

    @SuppressLint("ViewConstructor")
    class TopAlertView(
        context: Context,
        private val message: String,
        private val textColor: Int,
        private val iconRes: String?,
        private val iconColor: Int,
        private val backgroundColor: Int,
        private val lineColor: Int,
        private val font: Typeface,
        sizeWidth: Int,
    ) : FrameLayout(context) {

        // UI
        private val mCardView: CardView
        private val mLinearLayout: LinearLayout
        private val mLineView: View
        private val mTextView: TextView

        // Parameters
        private val mIconSize = 15.dpToPx
        private val mSideMargin = 15.dpToPx

        init {
            clipToPadding = false
            mCardView = CardView(context).apply {
                radius = 10F
                elevation = 20F
                setCardBackgroundColor(backgroundColor)
                // Setup LinearLayout
                mLinearLayout = LinearLayout(context).apply {
                    gravity = Gravity.CENTER_VERTICAL
                    orientation = LinearLayout.HORIZONTAL
                    // Setup Line View
                    mLineView = View(context).apply {
                        setBackgroundColor(lineColor)
                    }
                    addView(mLineView, LayoutPRM.Linear.get(8.dpToPx, LayoutPRM.MATCH))

                    // Setup TextView
                    mTextView = TextView(context).apply {
                        setTextColor(textColor)
                        setLineSpacing(3.dpToPx.toFloat(), 1F)
                        text = message
                        typeface = font
                    }
                    addView(
                        mTextView, LayoutPRM.Linear.availableWidthParams()
                            .margin(0, 0, mIconSize, 0)
                    )
                }
                addView(mLinearLayout, LayoutPRM.Card.get(LayoutPRM.MATCH, 65.dpToPx))
            }
            addView(
                mCardView, LayoutPRM.Frame.get(
                    sizeWidth - mSideMargin * 2,
                    LayoutPRM.WRAP
                ).gravity(Gravity.CENTER_HORIZONTAL)
                    .margin(mSideMargin)
            )
        }
    }
}
