package com.example.githubfollowers.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.githubfollowers.R
import com.example.githubfollowers.ui.searchResults.SearchResultsFragment
import com.example.githubfollowers.ui.SharedViewModel
import kotlinx.android.synthetic.main.get_followers_fragment.*


class GetFollowersFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.get_followers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        get_followers_button.setOnClickListener {
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

            when {
                !isConnected -> {
                    Toast.makeText(context, "Check your internet connection!", Toast.LENGTH_SHORT).show()
                }
                search_user_edit_text.text.isNullOrBlank() -> {
                    Toast.makeText(context, "Please enter a username!", Toast.LENGTH_SHORT).show()
                    search_user_edit_text.text.clear()
                }
                else -> {
                    transition()
                }
            }
        }
    }

    fun checkConnection(){

    }

    private fun transition(){
        viewModel.userName = search_user_edit_text.text.toString()

        val searchResultsFragment = SearchResultsFragment()
        val fragmentTransaction = fragmentManager?.beginTransaction()

        fragmentTransaction?.replace(R.id.nav_host_fragment, searchResultsFragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}