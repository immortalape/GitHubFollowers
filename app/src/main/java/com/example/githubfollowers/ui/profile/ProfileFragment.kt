package com.example.githubfollowers.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubfollowers.R
import com.example.githubfollowers.model.User
import com.example.githubfollowers.ui.SharedViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private val bundle = this.arguments?.getString("CLICKED_USER_NAME")


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
                ViewModelProviders.of(this).get(SharedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getUserData("octocat").observe(viewLifecycleOwner, Observer<User>
        { response ->
            if (response!=null) {
                bind(response)
            }else{
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
            }
        })

    }


    private fun bind(user: User) {

        val profileAvatar: ImageView =
            requireView().findViewById(R.id.profile_screen_image_view)
        Picasso.get().load(user.avatar_url).into(profileAvatar)

        profile_screen_username_text_view.text = user.login
        profile_screen_bio_text_view.text = user.bio
        profile_screen_public_gits_text_view.text = user.public_gists.toString()
        profile_screen_public_repos_text_view.text = user.public_repos.toString()
        profile_screen_followers_text_view.text = user.followers.toString()
        profile_screen_following_text_view.text = user.following.toString()
    }
}