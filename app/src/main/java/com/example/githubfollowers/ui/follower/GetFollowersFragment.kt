package com.example.githubfollowers.ui.follower

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubfollowers.R
import com.example.githubfollowers.ui.searchResults.SearchResultsFragment
import com.example.githubfollowers.ui.searchResults.SharedViewModel
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
            viewModel.userName = search_user_edit_text.text.toString()

            val homeFragment = SearchResultsFragment()
            val fragmentTransaction = fragmentManager?.beginTransaction()

            fragmentTransaction?.replace(R.id.nav_host_fragment, homeFragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }


    }



    
}