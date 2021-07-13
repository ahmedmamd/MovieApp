package com.example.movieappkotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappkotlin.R
import com.example.movieappkotlin.pojo.Myresponse
import kotlin.math.log

class MovieAdapter (
        var movie:ArrayList<Myresponse?>,
        val context: Context,
        ): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view:View): RecyclerView.ViewHolder(view) {
         var title : TextView
         var overview : TextView
         var averageRank : TextView
         var imagemovie :ImageView
         init {
             title = itemView.findViewById(R.id.title)
             overview = itemView.findViewById(R.id.overview)
             averageRank = itemView.findViewById(R.id.vote_average)
             imagemovie = itemView.findViewById(R.id.imageBoaster)
         }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false))
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.title.text = movie[position]!!.results.get(position).title
        holder.overview.text = movie[position]!!.results.get(position).overview
        holder.averageRank.text = movie[position]!!.results.get(position).vote_average.toString()
        Glide.with(context).load(movie[position]?.results?.get(position)?.poster_path).into(holder.imagemovie)
    }
    override fun getItemCount(): Int {
        return movie.size
    }
}