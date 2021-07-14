package com.example.movieappkotlin

import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappkotlin.adapter.MovieAdapter
import com.example.movieappkotlin.databinding.ActivityMainBinding
import com.example.movieappkotlin.homeViewmodel.HomeViewModel
import com.example.movieappkotlin.pojo.Myresponse
import com.example.movieappkotlin.pojo.ResultItem


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var movieList = ArrayList<ResultItem?>()
    var adapter = MovieAdapter(movieList , this)
    var homeViewModel:HomeViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this ,R.layout.activity_main)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setUPUi()
        setUPObserver()
    }

    private fun setUPUi() {
        homeViewModel!!.getPopularMovie(this)
         binding.recMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
         binding.recMovie.adapter = adapter
    }
    private fun setUPObserver() {
        homeViewModel!!.popularLiveData()!!.observe(this, Observer<Myresponse?> { response ->
            movieList.addAll(response.results)
            adapter.notifyDataSetChanged()
        })
    }
}

