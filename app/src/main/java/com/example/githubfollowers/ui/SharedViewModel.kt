package com.example.githubfollowers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubfollowers.api.Repository
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User

class SharedViewModel : ViewModel() {
    private val repository = Repository

    fun getFollowersData(user : String): LiveData<List<Followers>> {
        return repository.getFollowersData(user)
    }

    fun getUserData(user: String) : LiveData<User>{
        return repository.getUserData(user)
    }
}