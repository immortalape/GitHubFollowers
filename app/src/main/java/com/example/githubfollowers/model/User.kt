package com.example.githubfollowers.model

data class User (
	val avatarUrl: String,
	val login: String,
	val email: String,
	val followers: Int,
	val following: Int,
	val followersUrl: String,
	val followingUrl: String,
	val bio: String,
	val createdAt: String,
	val gistsUrl: String,
	val reposUrl: String,
	val publicGists: Int,
	val publicRepos: Int
)

