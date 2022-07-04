package com.example.githubuser_dicoding

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_dicoding.adapter.ListAdapter
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.databinding.ActivityMainBinding
import com.example.githubuser_dicoding.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: ArrayList<ResponseItem>
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.actiontitle)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.findListUser.observe(this) { Responseitem ->
            binding.idprogress.visibility = View.INVISIBLE
            showRecycler(Responseitem)
        }
        mainViewModel.searchListUser.observe(this) { Responsesearch ->
            binding.idprogress.visibility = View.INVISIBLE
            showRecycler(Responsesearch.items)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.idsearch).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = getString(R.string.queryHint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                binding.idprogress.visibility = View.VISIBLE
                mainViewModel.findSearchUser(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }



    private fun showRecycler(value: List<ResponseItem>) {
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