package com.example.retrofitsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitsamples.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var rv : RecyclerView
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter(this@MainActivity)

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                ApiUtils.getPostDAO().getPosts()
            }catch (e: IOException){
                Toast.makeText(this@MainActivity,e.toString(),Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException) {
                Toast.makeText(this@MainActivity, e.toString(), Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                adapter.posts = response.body()!!
            }else Toast.makeText(this@MainActivity, "Response no successful", Toast.LENGTH_SHORT).show()

            binding.progressBar.isVisible = false
        }

        val rv = binding.rv
        rv.adapter = adapter

        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}