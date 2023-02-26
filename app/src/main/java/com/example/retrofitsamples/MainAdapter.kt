package com.example.retrofitsamples

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitsamples.databinding.ItemCardBinding

class MainAdapter(val context : Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemCardBinding: ItemCardBinding = binding
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var posts: List<Posts>
        get() = differ.currentList
        set(value) { differ.submitList(value) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mainAdapter = posts[position]
        holder.itemCardBinding.postCard = mainAdapter

        val cardBinding = holder.itemCardBinding

        Glide.with(context).load("https://iconsplace.com/wp-content/uploads/_icons/ffffff/256/png/twitter-icon-18-256.png").into(cardBinding.ivLogo)
    }
}