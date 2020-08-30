package com.example.githubfollowers.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubfollowers.R
import com.example.githubfollowers.model.Followers
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    val context: FavoritesFragment,
    private val data : List<Followers>,
    private val itemClickListener : ItemClicked
) : RecyclerView.Adapter<FavoritesAdapter.Holder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.followers_list, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClicked(data[position])
        }
    }

    override fun getItemCount() = data.size


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val favoritesImageView : ImageView = itemView.findViewById(R.id.userImageView)
        private val favoritesUserName : TextView = itemView.findViewById(R.id.userNameTextView)


        fun bind(favorites: Followers){
            favoritesUserName.text = favorites.login
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                favoritesImageView.clipToOutline = true
            }
            Picasso.get().load(favorites.avatar_url).into(favoritesImageView)
        }
    }

    interface ItemClicked {
        fun onItemClicked(favorites: Followers)
    }
}