package com.guruthedev.instagram.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.guruthedev.instagram.R

@BindingAdapter("loadImage")
fun bindingImage(userImageView:ImageView,imageUri:String){
    Glide.with(userImageView.context)
        .load(imageUri)
        .placeholder(R.drawable.instagram)
        .error(R.drawable.error)
        .fitCenter()
        .circleCrop()
        .apply(RequestOptions.circleCropTransform())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .onlyRetrieveFromCache(true)
        .into(userImageView)
}
@BindingAdapter("loadImageUrl")
fun bindingImageLogo(userImageView:ImageView,imageUri:String){
    Glide.with(userImageView.context)
        .load(imageUri)
        .placeholder(R.drawable.instagram)
        .error(R.drawable.error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(userImageView)
}