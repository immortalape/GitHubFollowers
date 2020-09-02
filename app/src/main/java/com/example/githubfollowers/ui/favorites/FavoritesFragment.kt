package com.example.githubfollowers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.ui.profile.ProfileFragment
import com.example.githubfollowers.utils.DataService
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*

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
        val emptyView = inflater.inflate(R.layout.fragment_favorites_empty, container, false)
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        return if (adapter.itemCount == 0){
            emptyView
        } else {
            view.favorites_recycler_view.layoutManager = GridLayoutManager(context, 2)
            view.favorites_recycler_view.adapter = adapter
            view
        }
    }

    override fun onItemClicked(favorites: Followers) {
        val action = FavoritesFragmentDirections.navigateFromFavoritesToProfileFragment(favorites.login)
        findNavController().navigate(action)
    }
}