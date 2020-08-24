package com.example.githubfollowers.ui.favorites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.example.githubfollowers.ui.searchResults.SearchResultsAdapter
import com.example.githubfollowers.utils.DataService
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    val context: FavoritesFragment,
    private val data : List<Followers>,
    private val itemClickListener : ItemClicked
) : RecyclerView.Adapter<FavoritesAdapter.Holder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_list, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClicked(data[position])
        }
    }

    override fun getItemCount() = data.size


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val favoritesImageView : ImageView = itemView.findViewById(R.id.favorites_image_view)
        val favoritesUserName : TextView = itemView.findViewById(R.id.favorites_user_name_text_view)

        fun bind(followers: Followers){
            favoritesUserName.text = followers.login

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                favoritesImageView.clipToOutline = true
            }
            Picasso.get().load(followers.avatar_url).into(favoritesImageView)
        }
    }

    interface ItemClicked {
        fun onItemClicked(followers: Followers)
    }
}