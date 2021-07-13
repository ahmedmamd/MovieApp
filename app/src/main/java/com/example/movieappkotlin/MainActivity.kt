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


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var movieList = ArrayList<Myresponse?>()
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
            movieList.addAll(listOf(response))
            adapter.notifyDataSetChanged()
              Log.e("onChanged","onCreate: "+movieList.get(0)?.results?.get(10)?.title)
//            Log.e("onChanged", "onCreate: "+response.results.get(1).title)
//            Log.e("onChanged", "onCreate: "+response.results.get(2).title)
//            Log.e("onChanged", "onCreate: "+response.results.get(3).title)
        })
    }
}

