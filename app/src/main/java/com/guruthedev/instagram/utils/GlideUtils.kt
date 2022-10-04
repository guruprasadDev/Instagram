package com.guruthedev.instagram.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.guruthedev.instagram.R

fun loadImageUrl(
    imageURL: String,
    context: Context,
    height: Int,
    width: Int,
    imageView: ImageView,
) {
    Glide.with(context)
        .load(imageURL)
        .placeholder(R.drawable.instagram)
        .error(R.drawable.error)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(width, height)
        .onlyRetrieveFromCache(true)
        .into(imageView)
}

fun loadImageUrlLogo(
    imageURL: String,
    context: Context,
    height: Int,
    width: Int,
    imageView: ImageView,
) {
    Glide.with(context)
        .load(imageURL)
        .placeholder(R.drawable.instagram)
        .error(R.drawable.error)
        .fitCenter()
        .apply(circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(width, height)
        .onlyRetrieveFromCache(true)
        .into(imageView)
}

fun loadImageUrlForStory(
    imageURL: String,
    context: Context, imageView: ImageView,
) {
    Glide.with(context)
        .load(imageURL)
        .placeholder(R.drawable.instagram)
        .error(R.drawable.error)
        .fitCenter()
        .apply(circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)

}