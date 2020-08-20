package com.example.githubfollowers.model

data class User (
	val avatar_url: String,
	val login: String,
	val email: String? = null,
	val followers: Int,
	val following: Int,
	val followers_url: String,
	val following_url: String,
	val bio: String? = null,
	val created_at: String,
	val gists_url: String,
	val repos_url: String,
	val public_gists: Int,
	val public_repos: Int
)

