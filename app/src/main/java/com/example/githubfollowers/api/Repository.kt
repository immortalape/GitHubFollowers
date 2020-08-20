package com.example.githubfollowers.api

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository  {

    private val api = Retrofit.gitHubApi()

    fun getFollowersData(user: String) : LiveData<List<Followers>> {
        val liveData = MutableLiveData<List<Followers>>()

        api.getFollowerData(user).enqueue(object : Callback<List<Followers>> {
            override fun onResponse(
                call: Call<List<Followers>>,
                response: Response<List<Followers>>
            ) {
                liveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Followers>>, t: Throwable) {
                println("${t.message}")
            }
        })
        return liveData
    }

    fun getUserData(user: String) : LiveData<User> {

        val userLiveData = MutableLiveData<User>()

        api.getUserData(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                userLiveData.value = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("${t.message}")
            }
        })
        return userLiveData
    }
}