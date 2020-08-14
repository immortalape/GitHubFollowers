package com.example.githubfollowers.api

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
                //Handle the throwable
            }
        })
        return liveData
    }

    fun getUserData(user: String) : MutableLiveData<List<User>> {
        val userLiveData = MutableLiveData<List<User>>()

        api.getUserData(user).enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                userLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                //Handle throwable
            }
        })
        return userLiveData
    }
}