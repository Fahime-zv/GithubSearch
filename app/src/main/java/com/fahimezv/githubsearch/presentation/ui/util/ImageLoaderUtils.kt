package com.fahimezv.githubsearch.presentation.ui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.io.File
import com.squareup.picasso.Target

class ImageLoaderUtils private constructor(private val context: Context) {
    private var resource: Any? = null
    private var imageView: ImageView? = null
    private var placeholder: Int? = null
    private var placeholderDrawable: Drawable? = null
    private var loadListener: OnBitmapLoadListener? = null
    private var target: Target? = null

    fun load(res: Any): ImageLoaderUtils {
        this.resource = res
        return this
    }

    fun placeholder(@DrawableRes placeholder: Int): ImageLoaderUtils {
        this.placeholder = placeholder
        return this
    }

    fun placeholder(placeholder: Drawable): ImageLoaderUtils {
        placeholderDrawable = placeholder
        return this
    }

    fun into(imageView: ImageView) {
        this.imageView = imageView
        build()
    }

    fun into(listener: OnBitmapLoadListener) {
        loadListener = listener
        build()
    }

    private fun build() {
        val picasso = Picasso.get()
        val requestCreator: RequestCreator = when (resource) {
            is Uri -> {
                picasso.load(resource as Uri)
            }
            is String -> {
                picasso.load(resource as String)
            }
            is File -> {
                picasso.load((resource as File))
            }
            is Int -> {
                picasso.load((resource as Int))
            }

            else -> throw IllegalArgumentException("load() argument must be String or File or ResourceId or Uri")
        }

        requestCreator.tag(context)
        requestCreator.apply {

        }
        placeholder?.let { requestCreator.placeholder(it) }
        placeholderDrawable?.let { requestCreator.placeholder(it) }
        imageView?.let { requestCreator.into(it) }
        loadListener?.let {
            target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    it.onBitmapLoaded(bitmap)
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                    it.onBitmapFailed()
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    it.onBitmapPrepareLoad()
                }
            }.also { t: Target ->
                requestCreator.into(t)
            }
        }
    }

    interface OnBitmapLoadListener {
        fun onBitmapPrepareLoad()
        fun onBitmapLoaded(bitmap: Bitmap?)
        fun onBitmapFailed()
    }


    companion object {
        fun with(context: Context): ImageLoaderUtils {
            return ImageLoaderUtils(context)
        }
    }
}