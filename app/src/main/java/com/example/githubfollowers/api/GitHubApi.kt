package com.example.githubfollowers.api

import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{user}/followers")
    fun getFollowerData(@Path("user") user : String) : Call<List<Followers>>

    @GET ("users/{user}")
    fun getUserData(@Path("user") user: String) : Call<List<User>>
}