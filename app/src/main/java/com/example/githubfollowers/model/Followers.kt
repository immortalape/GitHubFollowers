package com.example.githubfollowers.model

data class Followers (
    val login : String,
    val avatar_url: String
) {
    operator fun get(adapterPosition: Int): Followers {
        return get(adapterPosition)
    }
}