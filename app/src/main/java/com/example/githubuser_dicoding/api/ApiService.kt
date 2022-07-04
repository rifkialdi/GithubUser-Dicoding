package com.example.githubuser_dicoding.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    @Headers("Authorization: token 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")
    fun getListUser(): Call<List<ResponseItem>>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<ResponseDetail>

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") q: String
    ): Call<ResponseSearch>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ResponseItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ResponseItem>>
}