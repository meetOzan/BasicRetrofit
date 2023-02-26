package com.example.retrofitsamples

import com.example.retrofitsamples.Constants.BASE_URL

object ApiUtils {
    fun getPostDAO(): PostService =
        RetrofitClient.getClient(BASE_URL).create(PostService::class.java)
}