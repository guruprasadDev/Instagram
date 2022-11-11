package com.guruthedev.instagram.data

data class Post(val items: ArrayList<RecyclerData>)
data class RecyclerData(val name: String, val description: String,val owner: Owner)
data class Owner(val avatar_url:String)