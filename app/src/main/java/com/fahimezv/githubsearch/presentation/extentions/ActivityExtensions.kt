package com.fahimezv.githubsearch.presentation.extentions

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display

/**
 * A convenience Activity property extension to read device screen width just like a normal android stuff
 *
 * Example:
 *```
 * class MainActivity : AppCompatActivity()  {
 *      override fun onCreate(savedInstanceState: Bundle?) {
 *          createView(this.screenWidth)
 *      }
 * }
 * ```
 *
 * @receiver the activity which screenWidth will be calculated in
 * @return device Screen Width
 * */
@Suppress("DEPRECATION")
inline val Activity.screenWidth: Int
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val displayMetrics = DisplayMetrics()
            val display: Display? = display
            display!!.getRealMetrics(displayMetrics)
            displayMetrics.widthPixels
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }
