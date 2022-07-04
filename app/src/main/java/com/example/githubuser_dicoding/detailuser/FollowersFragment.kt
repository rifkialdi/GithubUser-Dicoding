package com.example.githubuser_dicoding.detailuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser_dicoding.adapter.ListAdapter
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.databinding.FragmentFollowersBinding
import com.example.githubuser_dicoding.viewmodel.DetailViewModel
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getLogin = activity?.intent?.getStringExtra("login")!!

        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.findListFollowers(getLogin)
        detailViewModel.findListFollowers.observe(viewLifecycleOwner) { Responseitem ->
            showRecycler(Responseitem)
        }
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
    }


}