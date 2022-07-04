package com.example.githubuser_dicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseDetail
import com.example.githubuser_dicoding.databinding.ActivityDetailUserBinding
import com.example.githubuser_dicoding.detailuser.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        private val TAB_TITLES = arrayOf(
            "Followers",
            "Following"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataLogin = intent.getStringExtra("login")
        binding.idtvLogin.text = dataLogin

        findDetail(dataLogin!!)

        val sectionPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.idviewpager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.idtablayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
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
                    binding.idtvRepository.text = "Repository : ${responseBody.publicRepo}"
                    binding.idtvFollowers.text = "Followers : ${responseBody.followers}"
                    binding.idtvFollowing.text = "Following : ${responseBody.following}"
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
            }

        })
    }
}