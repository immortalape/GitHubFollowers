package com.example.githubfollowers.ui.searchResults

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.squareup.picasso.Picasso


class SearchResultsAdapter(
    val context: SearchResultsFragment,
    private val followers: MutableList<Followers>,
    private val itemClickListener : ItemClicked
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.followers_list, parent, false))
    }

    override fun getItemCount() = followers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(followers[position])
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClicked(followers[position])
        }
    }


    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {

        lateinit var followers : Followers

        private val followerImageView : ImageView = view.findViewById(R.id.userImageView)
        private val followersLogin: TextView = view.findViewById(R.id.userNameTextView)


        fun bind(followers: Followers){
            this.followers = followers
            followersLogin.text = followers.login
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                followerImageView.clipToOutline = true
            }
            Picasso.get().load(followers.avatar_url).into(followerImageView)
        }
    }


    fun updateFollowersList(followers: List<Followers>){
        this.followers.addAll(followers)
        notifyDataSetChanged()
    }

    interface ItemClicked {
        fun onItemClicked(followers: Followers)
    }
}