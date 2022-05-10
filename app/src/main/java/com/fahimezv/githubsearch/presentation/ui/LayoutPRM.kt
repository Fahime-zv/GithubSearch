package com.fahimezv.githubsearch.presentation.ui

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.updateMarginsRelative
import com.fahimezv.githubsearch.presentation.extentions.dpToPx

/**
 * Helper class of [ViewGroup.LayoutParams]  for ease of adding views in different ViewGroups
 */
class LayoutPRM {

    companion object {
        /**
         * this value is defined only for shorter and cleaner code representing [ViewGroup.LayoutParams.MATCH_PARENT]
         */
        const val MATCH = ViewGroup.LayoutParams.MATCH_PARENT

        /**
         * this value is defined only for shorter and cleaner code representing [ViewGroup.LayoutParams.WRAP_CONTENT]
         */
        const val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    /**
     * Helper class of [LinearLayout.LayoutParams]  for ease of adding views inside [LinearLayout]
     *
     * Example:
     * ```
     * linearLayout.apply{
     *     addView(view, NZLayout.Linear.defaultParams())
     * }
     * ```
     *
     * Adding the view with specific weight
     * @param width width of view inside of [LinearLayout].
     * it can be specific number or [MATCH] or [WRAP]
     * @param height height of view inside of [LinearLayout].
     * it can be specific number or [MATCH] or [WRAP]
     * @param weight desired weight of view in [LinearLayout] default value of 0F
     * @constructor creates [LinearLayout.LayoutParams] with given values
     */
    class Linear(width: Int, height: Int, weight: Float = 0F) :
        LinearLayout.LayoutParams(width, height, weight) {
        /**
         * A function for customizing the layout_gravity attribute
         * @param gravity desired gravity
         * @return same [LinearLayout.LayoutParams] after applying gravity to it
         */
        fun gravity(gravity: Int): Linear {
            this.gravity = gravity
            return this
        }

        companion object {
            /**
             * creates layoutParam with values
             * * width: [MATCH]
             * * height: [WRAP]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun defaultParams() = Linear(MATCH, WRAP)

            /**
             * creates layoutParam with values of:
             * * width: [WRAP]
             * * height: [WRAP]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun wrapContent() = Linear(WRAP, WRAP)

            /**
             * creates layoutParam with values of :
             * * width: [MATCH]
             * * height: [MATCH]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun fullScreen() = Linear(MATCH, MATCH)

            /**
             * creates layoutParam with values of :
             * * width: [MATCH]
             * * height: 0
             * * weight: [weight] & default value: 1F
             * @param weight desired weight
             * @return defined [LinearLayout.LayoutParams]
             */
            fun availableHeightParams(weight: Float = 1F) = Linear(MATCH, 0, weight)

            /**
             * creates layoutParam with values of :
             * * width: [0]
             * * height: [WRAP]
             * * weight: [weight] & default value: 1F
             * @param weight desired weight
             * @return defined [LinearLayout.LayoutParams]
             */
            fun availableWidthParams(weight: Float = 1F) = Linear(0, WRAP, weight)

            /**
             * creates layoutParam with values of :
             * * width: 0
             * * height: 1
             * * weight: 1F
             * @return defined [LinearLayout.LayoutParams]
             */
            fun justOccupyWidth() = Linear(0, 1, 1F)

            /**
             * creates layoutParam with values of :
             * * width: 1
             * * height: 0
             * * weight: 1F
             * @return defined [LinearLayout.LayoutParams]
             */
            fun justOccupyHeight() = Linear(1, 0, 1F)

            /**
             * creates layoutParam with values of :
             * * width: [MATCH]
             * * height: [MATCH]
             * * weight: 1F
             * @return defined [LinearLayout.LayoutParams]
             */
            fun justOccupyScreen() = Linear(MATCH, MATCH, 1F)

            /**
             * creates layoutParam with values of :
             * * width: [w]
             * * height: [h]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun get(w: Int, h: Int) = Linear(w, h)

            /**
             * creates layoutParam with values of :
             * * width: [w]
             * * height: [w]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun get(w: Int) = Linear(w, w)

            /**
             * creates layoutParam with values of :
             * * width: [MATCH]
             * * height: 1dp
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun horizontalLine() = get(MATCH, 1.dpToPx)

            /**
             * creates layoutParam with values of :
             * * width: 1dp
             * * height: [MATCH]
             * * weight: 0
             * @return defined [LinearLayout.LayoutParams]
             */
            fun verticalLine() = get(1.dpToPx, MATCH)
        }
    }

    /**
     * Helper class of [FrameLayout.LayoutParams]  for ease of adding views inside [FrameLayout]
     *
     * Example:
     * ```
     * frameLayout.apply{
     *     addView(view, NZLayout.Frame.defaultParams())
     * }
     * ```
     *
     * @param width width of the view inside of [FrameLayout].
     * it can be specific number or [MATCH] or [WRAP]
     * @param height height of the view inside of [FrameLayout].
     * it can be specific number or [MATCH] or [WRAP]
     * @constructor creates [FrameLayout.LayoutParams] with given values
     */
    class Frame(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {
        /**
         * A function for customizing the layout_gravity attribute
         * @param gravity desired gravity
         * @return same [FrameLayout.LayoutParams] after applying gravity to it
         */
        fun gravity(gravity: Int): Frame {
            this.gravity = gravity
            return this
        }

        companion object {
            /**
             * creates layoutParam with values
             * * width: [MATCH]
             * * height: [WRAP]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun defaultParams() = Frame(MATCH, WRAP)

            /**
             * creates layoutParam with values
             * * width: [WRAP]
             * * height: [WRAP]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun wrapContent() = Frame(WRAP, WRAP)

            /**
             * creates layoutParam with values
             * * width: [MATCH]
             * * height: [MATCH]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun fullScreen() = Frame(MATCH, MATCH)

            /**
             * creates layoutParam with values
             * * width: [w]
             * * height: [h]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun get(w: Int, h: Int) = Frame(w, h)

            /**
             * creates layoutParam with values
             * * width: [w]
             * * height: [w]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun get(w: Int) = Frame(w, w)

            /**
             * creates layoutParam with values of :
             * * width: [MATCH]
             * * height: 1dp
             * * weight: 0
             * @return defined [FrameLayout.LayoutParams]
             */
            fun horizontalLine() = Linear.get(MATCH, 1.dpToPx)

            /**
             * creates layoutParam with values of :
             * * width: 1dp
             * * height: [MATCH]
             * * weight: 0
             * @return defined [FrameLayout.LayoutParams]
             */
            fun verticalLine() = Linear.get(1.dpToPx, MATCH)
        }
    }

    /**
     * Helper class of  [FrameLayout.LayoutParams]  for ease of adding views inside [CardView]
     *
     * Example: same as [Linear] example
     * @param width width of the view inside of [CardView].
     * it can be specific number or [MATCH] or [WRAP]
     * @param height height of the view inside of [CardView].
     * it can be specific number or [MATCH] or [WRAP]
     * @constructor creates [FrameLayout.LayoutParams] with given values
     */
    class Card(width: Int, height: Int) : FrameLayout.LayoutParams(width, height) {

        companion object {
            /**
             * creates layoutParam with values
             * * width: [MATCH]
             * * height: [WRAP]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun defaultParams() = Card(MATCH, WRAP)

            /**
             * creates layoutParam with values
             * * width: [WRAP]
             * * height: [WRAP]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun wrapContent() = Card(WRAP, WRAP)

            /**
             * creates layoutParam with values
             * * width: [MATCH]
             * * height: [MATCH]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun fullScreen() = Card(MATCH, MATCH)

            /**
             * creates layoutParam with values
             * * width: [w]
             * * height: [h]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun get(w: Int, h: Int) = Card(w, h)

            /**
             * creates layoutParam with values
             * * width: [w]
             * * height: [w]
             * @return defined [FrameLayout.LayoutParams]
             */
            fun get(w: Int) = Card(w, w)
        }
    }

}

/**
 *  A convenience [ViewGroup.MarginLayoutParams] extension to setMargin in layout params
 *
 *  Example:
 *  ```
 *  NZLayout.Linear.fullScreen().margin(8.dpToPx)
 *  ```
 *  @receiver ViewGroup.MarginLayoutParams which can be provided by NZLayout
 *  @param value desired margin
 */
fun ViewGroup.MarginLayoutParams.margin(value: Int) = apply {
    updateMarginsRelative(value, value, value, value)
}

/**
 *  A convenience [ViewGroup.MarginLayoutParams] extension to setMargin in layout params
 *
 *  Example:
 *  ```
 *  NZLayout.Linear.fullScreen().margin(8.dpToPx,0,8.dpToPx,0)
 *  ```
 *  @receiver ViewGroup.MarginLayoutParams which can be provided by NZLayout
 *  @param start desired marginStart
 *  @param top desired marginTop
 *  @param end desired marginEnd
 *  @param bottom desired marginBottom
 */
fun ViewGroup.MarginLayoutParams.margin(start: Int, top: Int, end: Int, bottom: Int) = apply {
    updateMarginsRelative(start, top, end, bottom)
}
