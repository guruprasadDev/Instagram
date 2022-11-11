package com.guruthedev.instagram.network

import com.guruthedev.instagram.data.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String): Post
}