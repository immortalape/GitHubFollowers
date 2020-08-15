package com.example.githubfollowers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubfollowers.api.Repository
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User

class SharedViewModel : ViewModel() {

    private val repository = Repository
    private val liveData =  MutableLiveData<String>()
    var userName = ""

    fun sendUserName(txt: String) {
        liveData.value = txt
    }

    fun getFollowersData(user : String): LiveData<List<Followers>> {
        return repository.getFollowersData(user)
    }

    fun getUserData(user: String) : LiveData<List<User>>{
        return repository.getUserData(user)
    }
}