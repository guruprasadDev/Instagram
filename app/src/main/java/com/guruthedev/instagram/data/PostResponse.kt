package com.guruthedev.instagram.data

import com.google.gson.annotations.SerializedName

data class PostResponse(val items: ArrayList<Post>)
data class Post(val name: String, val description: String,val owner: Owner)
data class Owner(
    @SerializedName("avatar_url")
    val postImage:String)
