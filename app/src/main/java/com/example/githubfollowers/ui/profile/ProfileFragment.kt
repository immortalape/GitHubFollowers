package com.example.githubfollowers.ui.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.model.User
import com.example.githubfollowers.ui.SharedViewModel
import com.example.githubfollowers.ui.searchResults.SearchResultsFragment
import com.example.githubfollowers.utils.DataService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    val args : ProfileFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
                ViewModelProviders.of(this).get(SharedViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val login = args.login

        sharedViewModel.getUserData(login).observe(viewLifecycleOwner, Observer<User>
        { response ->
            if (response!=null) {
                bind(response)
                view.profile_screen_add_to_favorites_button.setOnClickListener {
                    addToFavorites(response)
                }
                view.profile_screen_get_followers_button.setOnClickListener {
                    val action = ProfileFragmentDirections.navigateFromProfileToSearchResults("${response.login}")
                    findNavController().navigate(action)
                }
            }else{
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
        return view
    }

    private fun addToFavorites(user: User) {
        if (Followers(user.login, user.avatar_url) in DataService.users){
            Toast.makeText(context, "User already in favorites", Toast.LENGTH_SHORT).show()
        }else{
            DataService.users.add(Followers(user.login, user.avatar_url))
            Toast.makeText(context, "User added to your favorites", Toast.LENGTH_SHORT).show()
        }
    }


    private fun bind(user: User) {
        val profileAvatar: ImageView =
            requireView().findViewById(R.id.profile_screen_image_view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            profileAvatar.clipToOutline = true
        }
        Picasso.get().load(user.avatar_url).into(profileAvatar)

        profile_screen_username_text_view.text = user.login
        profile_screen_bio_text_view.text = user.bio
        profile_screen_location_text_view.text = user.location
        profile_screen_public_gists_text_view.text = getString(R.string.public_gists, user.public_gists)
        profile_screen_public_repos_text_view.text = getString(R.string.public_repos, user.public_repos)
        profile_screen_followers_text_view.text = getString(R.string.followers, user.followers)
        profile_screen_following_text_view.text = getString(R.string.following, user.following)
    }
}