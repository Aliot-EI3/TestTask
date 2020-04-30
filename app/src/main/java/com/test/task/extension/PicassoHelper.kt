package com.test.task.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun getPicasso(): Picasso = Picasso.get()

fun loadImageByUrl(url: String): RequestCreator =
    getPicasso()
        .load(url)

fun setImageByUrlResize(url: String, targetWidth: Int, targetHeight: Int): RequestCreator =
    loadImageByUrl(url).resize(targetWidth, targetHeight).centerCrop()

fun ImageView.setImage(url: String) {
    loadImageByUrl(url).into(this)
}

fun ImageView.setImageResize(
    url: String,
    targetWidth: Int = TARGET_WIDTH,
    targetHeight: Int = TARGET_HEIGHT
) {
    setImageByUrlResize(url, targetWidth, targetHeight).into(this)
}

const val TARGET_WIDTH = 500
const val TARGET_HEIGHT = 500