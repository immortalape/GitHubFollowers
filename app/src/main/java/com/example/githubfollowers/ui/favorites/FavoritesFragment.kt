package com.example.githubfollowers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.ui.profile.ProfileFragment
import com.example.githubfollowers.utils.DataService
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), FavoritesAdapter.ItemClicked {

    private lateinit var favoritesViewModel: FavoritesViewModel
    var adapter = FavoritesAdapter(this, DataService.users, this)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favorites_recycler_view.layoutManager = LinearLayoutManager(context)
        favorites_recycler_view.adapter = adapter

    }

    override fun onItemClicked(followers: Followers) {
        val profileFragment = ProfileFragment(followers.login)
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, profileFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}