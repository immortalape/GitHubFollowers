package com.example.githubfollowers.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubfollowers.api.Repository
import com.example.githubfollowers.model.User

class ProfileViewModel : ViewModel() {

    private val repository = Repository


    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text
}