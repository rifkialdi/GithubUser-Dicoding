package com.example.githubuser_dicoding.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser_dicoding.DetailUserActivity
import com.example.githubuser_dicoding.R
import com.example.githubuser_dicoding.api.ResponseItem
import com.example.githubuser_dicoding.databinding.ListItemBinding

class ListAdapter(val dataListItem: ArrayList<ResponseItem>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataListItem[position]
        val context = holder.itemView.context

        holder.binding.idtvListname.text = data.login
        Glide.with(holder.itemView)
            .load(data.avatarUrl)
            .placeholder(R.drawable.wait)
            .circleCrop()
            .into(holder.binding.idimagelist)
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data.login, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailUserActivity::class.java)
            intent.putExtra("login", data.login)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return dataListItem.size
    }

}