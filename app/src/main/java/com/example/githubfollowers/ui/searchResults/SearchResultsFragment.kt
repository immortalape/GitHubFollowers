package com.example.githubfollowers.ui.searchResults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import com.example.githubfollowers.ui.SharedViewModel
import com.example.githubfollowers.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_search_results.*


class SearchResultsFragment : Fragment(), SearchResultsAdapter.ItemClicked {

    private lateinit var sharedViewModel: SharedViewModel
    private var adapter = SearchResultsAdapter(this, mutableListOf(), this)

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

        if (adapter.itemCount == 0) {
            sharedViewModel.getFollowersData(sharedViewModel.userName).observe(viewLifecycleOwner, Observer<List<Followers>>
            { response ->
                if(response!=null){
                    adapter.updateFollowersList(response)
                    Toast.makeText(context, "Successfully retrieved data!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(context, "Data already retrieved!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(followers: Followers) {
        val profileFragment = ProfileFragment(followers.login)
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, profileFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}