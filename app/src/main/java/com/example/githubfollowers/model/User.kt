package com.example.githubfollowers.model

data class User (
	val avatarUrl: String? = null,
	val login: String? = null,
	val email: Any? = null,
	val followers: Int? = null,
	val following: Int? = null,
	val followersUrl: String? = null,
	val followingUrl: String? = null,
	val bio: String? = null,
	val createdAt: String? = null,
	val gistsUrl: String? = null,
	val reposUrl: String? = null,
	val publicGists: Int? = null,
	val publicRepos: Int? = null
)

