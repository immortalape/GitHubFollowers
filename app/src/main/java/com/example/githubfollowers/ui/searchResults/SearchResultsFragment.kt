package com.example.githubfollowers.ui.searchResults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.ui.SharedViewModel
import com.example.githubfollowers.ui.home.GetFollowersFragment
import com.example.githubfollowers.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_search_results.*
import kotlinx.android.synthetic.main.fragment_search_results.view.*


class SearchResultsFragment: Fragment(), SearchResultsAdapter.ItemClicked {

    private lateinit var sharedViewModel: SharedViewModel
    private var adapter = SearchResultsAdapter(this, mutableListOf(), this)
    private val args : SearchResultsFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
                ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_search_results, container, false)


        view.homeScreenRecyclerView.layoutManager = GridLayoutManager(context, 2)
        view.homeScreenRecyclerView.adapter = adapter
        val login = args.login

        if (adapter.itemCount == 0) {
            sharedViewModel.getFollowersData(login).observe(viewLifecycleOwner, Observer<List<Followers>>
            { response ->
                if(response!=null){
                    adapter.updateFollowersList(response)
                    Toast.makeText(context, "Successfully retrieved data!", Toast.LENGTH_SHORT).show()
                }else{
                    AlertDialog.Builder(requireContext())
                        .setMessage("User doesn't exist")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setOnDismissListener {
                            findNavController().navigate(R.id.navigation_get_followers)
                        }
                        .create().show()
                }
            })
        }

        return view
    }

    override fun onItemClicked(followers: Followers) {
        val action = SearchResultsFragmentDirections.navigateToProfileFragment("${followers.login}")
        findNavController().navigate(action)
    }
}