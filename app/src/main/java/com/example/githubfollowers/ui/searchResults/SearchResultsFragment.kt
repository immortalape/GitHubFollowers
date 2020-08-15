package com.example.githubfollowers.ui.searchResults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import com.example.githubfollowers.ui.SharedViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*


class SearchResultsFragment : Fragment(), SearchResultsFragmentAdapter.ItemClicked {

    private lateinit var sharedViewModel: SharedViewModel
    private var adapter = SearchResultsFragmentAdapter(this, mutableListOf(), this)



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
                ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeScreenRecyclerView.layoutManager = GridLayoutManager(context, 2)
        homeScreenRecyclerView.adapter = adapter


        sharedViewModel.getFollowersData(sharedViewModel.userName).observe(viewLifecycleOwner, Observer<List<Followers>>
        { response ->
            if(response!=null){
                adapter.updateFollowersList(response)
                Toast.makeText(context, "Successfully retrieved data!", Toast.LENGTH_SHORT).show()
//                println("$response")
            }else{
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        }
        )
    }

    override fun onItemClicked(followers: Followers) {
        Toast.makeText(context, followers.login, Toast.LENGTH_SHORT).show()
        sharedViewModel.getUserData(followers.login).observe(viewLifecycleOwner, Observer<List<User>>
        { response ->
            if (response!=null) {
                println(response)
            }else {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}