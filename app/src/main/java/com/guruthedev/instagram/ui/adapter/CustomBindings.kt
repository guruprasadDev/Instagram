package com.guruthedev.instagram.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun bindingImage(userImageView:ImageView,imageUri:String){
    Glide.with(userImageView.context)
        .load(imageUri)
        .into(userImageView)
}