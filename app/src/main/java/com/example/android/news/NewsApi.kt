package com.example.android.news

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("v2/top-headlines?country=us&category=business&apiKey=b2cc4d22e2084e43beab9a4fc7f3d66b")
    fun getNews():Call<Articles>
        
    }
