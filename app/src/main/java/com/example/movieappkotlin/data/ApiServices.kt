package com.example.movieappkotlin.data

import android.content.Context
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 open class ApiServices {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    //    private static String BASE_URL;
    private var apiInterface: ApiInterface? = null
   open fun getINSTANCE(context: Context?): ApiInterface? {
        val client = OkHttpClient.Builder()
            .build()
        if (apiInterface == null) {
            apiInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(ApiInterface::class.java)
                //apiInterface = retrofit.create(ApiInterface.class);
        }
        return apiInterface
    }
}