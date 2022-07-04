package com.example.githubuser_dicoding.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getListUser(): Call<List<ResponseItem>>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseDetail>

    @GET("search/users")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<ResponseSearch>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ResponseItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ResponseItem>>
}