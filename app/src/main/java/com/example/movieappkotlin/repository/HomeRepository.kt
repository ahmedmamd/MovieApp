package com.example.movieappkotlin.repository

import android.content.Context
import com.example.movieappkotlin.data.ApiInterface
import com.example.movieappkotlin.data.ApiServices
import com.example.movieappkotlin.pojo.Myresponse
import io.reactivex.rxjava3.core.Observable

class HomeRepository {
    var apiInterface: ApiInterface? = null
    val API_KEY = "9c02c9ddb0ce19bf0dabd869597c0aaf"
    val PAGE = 1
    val LANGUAGE = "en-US"

    fun popular(context: Context?): Observable<Myresponse> {
        apiInterface = ApiServices().getINSTANCE(context)
        return apiInterface!!.popular(API_KEY, LANGUAGE, PAGE)
    }
}