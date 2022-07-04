package com.example.githubuser_dicoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuser_dicoding.api.ResponseDetail
import com.example.githubuser_dicoding.databinding.ActivityDetailUserBinding
import com.example.githubuser_dicoding.detailuser.SectionsPagerAdapter
import com.example.githubuser_dicoding.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel

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
        supportActionBar?.title = getString(R.string.actiontitle)

        val dataLogin = intent.getStringExtra("login")!!

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.findDetail(dataLogin)
        detailViewModel.detailInfo.observe(this){ Responsedetail ->
            binding.idprogress.visibility = View.INVISIBLE
            detailInfo(Responsedetail)
        }


        val sectionPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.idviewpager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.idtablayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }

    fun detailInfo(value : ResponseDetail) {
        Glide.with(binding.root)
            .load(value.avatarUrl)
            .placeholder(R.drawable.wait)
            .centerCrop()
            .into(binding.idimgDetail)
        binding.idtvName.text = value.name
        binding.idtvLogin.text = value.login
        binding.idtvRepository.text = "Repository : ${value.publicRepo}"
        binding.idtvFollowers.text = "Followers : ${value.followers}"
        binding.idtvFollowing.text = "Following : ${value.following}"
    }


}