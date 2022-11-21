package com.guruthedev.instagram.network

import com.guruthedev.instagram.data.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    suspend fun getFeedPosts(@Query("q") query: String): PostResponse
}