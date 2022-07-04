package com.example.githubuser_dicoding

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_dicoding.adapter.ListAdapter
import com.example.githubuser_dicoding.api.ApiConfig
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.api.ResponseSearch
import com.example.githubuser_dicoding.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: ArrayList<ResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findListUserGithub()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.idsearch).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Look For People"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                findSearchUser(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    fun findListUserGithub() {
        val client = ApiConfig.getApiService().getListUser()
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(call: Call<List<ResponseItem>>, response: retrofit2.Response<List<ResponseItem>>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    showRecycler(responseBody)
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
                    showRecycler(responseBody.items)
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
            }
        })
    }

    fun showRecycler(value: List<ResponseItem>) {
        data = arrayListOf()
        for (item in value) {
            data.add(ResponseItem(item.login, item.url, item.avatarUrl))
        }

        binding.idrecyclerview.apply {
            adapter = ListAdapter(data)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}