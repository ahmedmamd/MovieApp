package com.example.movieappkotlin.data

import com.example.movieappkotlin.pojo.Myresponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun  popular(@Query("api_key") api_key:String,
                 @Query("language") language:String,
                 @Query("page") page:Int  ):Observable<Myresponse>
}