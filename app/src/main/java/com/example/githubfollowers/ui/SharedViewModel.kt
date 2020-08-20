package com.example.githubfollowers.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubfollowers.api.Repository
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import com.example.githubfollowers.utils.FragmentTransaction

class SharedViewModel : ViewModel() {

    private val repository = Repository
    val fragmentTransaction = FragmentTransaction
    var userName = ""
    var followerLogin = ""

    fun getFollowersData(user : String): LiveData<List<Followers>> {
        return repository.getFollowersData(user)
    }

    fun getUserData(user: String) : LiveData<User>{
        return repository.getUserData(user)
    }

    fun transaction(fragment: Fragment) {
        fragmentTransaction.transaction(fragment)
    }

}