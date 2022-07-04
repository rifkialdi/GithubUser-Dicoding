package com.example.githubuser_dicoding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.api.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _findListUser = MutableLiveData<List<ResponseItem>>()
    val findListUser: LiveData<List<ResponseItem>> = _findListUser

    private val _searchListUser = MutableLiveData<ResponseSearch>()
    val searchListUser: LiveData<ResponseSearch> = _searchListUser

    init {
        findListUserGithub()
    }

    private fun findListUserGithub() {
        val client = ApiConfig.getApiService().getListUser()
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(call: Call<List<ResponseItem>>, response: Response<List<ResponseItem>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    _findListUser.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
            }
        })
    }

    fun findSearchUser(username: String) {
        val client = ApiConfig.getApiService().getSearchUser(username)
        client.enqueue(object : Callback<ResponseSearch> {
            override fun onResponse(
                call: Call<ResponseSearch>,
                response: Response<ResponseSearch>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    _searchListUser.value = responseBody
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
            }
        })
    }
}