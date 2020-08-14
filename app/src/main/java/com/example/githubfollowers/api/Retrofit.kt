package com.example.githubfollowers.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private fun retrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun gitHubApi(): GitHubApi {
        return retrofitInstance().create(GitHubApi::class.java)
    }
}