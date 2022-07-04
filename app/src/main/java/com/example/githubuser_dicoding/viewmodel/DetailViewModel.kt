package com.example.githubuser_dicoding.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseDetail
import com.example.githubuser_dicoding.api.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _findListFollowers = MutableLiveData<List<ResponseItem>>()
    val findListFollowers: LiveData<List<ResponseItem>> = _findListFollowers

    private val _findListFollowing = MutableLiveData<List<ResponseItem>>()
    val findListFollowing: LiveData<List<ResponseItem>> = _findListFollowing

    private val _detailInfo = MutableLiveData<ResponseDetail>()
    val detailInfo: LiveData<ResponseDetail> = _detailInfo

    fun findDetail(username: String) {
        val retrofit = ApiConfig.getApiService().getDetailUser(username)
        retrofit.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    _detailInfo.value = responseBody
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
            }
        })
    }

    fun findListFollowers(username: String) {
        val retrofit = ApiConfig.getApiService().getFollowers(username)
        retrofit.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    _findListFollowers.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
            }
        })
    }

    fun findListFollowing(username: String) {
        val retrofit = ApiConfig.getApiService().getFollowing(username)
        retrofit.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    _findListFollowing.value = responseBody
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
            }

        })
    }
}