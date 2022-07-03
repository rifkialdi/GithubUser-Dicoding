package com.example.githubuser_dicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseDetail
import com.example.githubuser_dicoding.databinding.ActivityDetailUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataLogin = intent.getStringExtra("login")
        binding.idtvLogin.text = dataLogin

        findDetail(dataLogin!!)
    }

    fun findDetail(username: String) {
        val retrofit = ApiConfig.getApiService().getDetailUser(username)
        retrofit.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!

                    binding.idtvName.text = responseBody.name
                    Glide.with(binding.root)
                        .load(responseBody.avatarUrl)
                        .placeholder(R.drawable.wait)
                        .centerCrop()
                        .into(binding.idimgDetail)
                    binding.idtvFollowers.text = "Followers : ${responseBody.followers}"
                    binding.idtvFollowing.text = "Following : ${responseBody.following}"
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
            }

        })
    }
}