package com.example.githubuser_dicoding.detailuser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_dicoding.R
import com.example.githubuser_dicoding.adapter.ListAdapter
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.databinding.FragmentFollowersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getLogin = activity?.intent?.getStringExtra("login")
        findListFollowers(getLogin!!)
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
                    Log.e("TAG", "followers : $responseBody", )
                    showRecycler(responseBody)
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                Log.e("TAG", "followers : ${t.message}", )
            }

        })
    }

    fun showRecycler(value: List<ResponseItem>) {
        val data = arrayListOf<ResponseItem>()
        for (item in value) {
            data.add(ResponseItem(item.login, item.url, item.avatarUrl))
        }
        binding.idrecyclerviewFollowers.apply {
            adapter = ListAdapter(data)
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.e("TAG", "onDestroy: followers", )
    }


}