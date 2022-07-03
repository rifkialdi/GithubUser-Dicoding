package com.example.githubuser_dicoding.api

import com.google.gson.annotations.SerializedName

data class ResponseItem(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,
)

data class ResponseDetail(
	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("followers")
	val followers: String,

	@field:SerializedName("following")
	val following: String,
)
data class ResponseSearch(
	@field:SerializedName("items")
	val items: List<ResponseItem>
)
