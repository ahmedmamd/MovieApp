package com.example.movieappkotlin.homeViewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappkotlin.data.ApiInterface
import com.example.movieappkotlin.data.ApiServices
import com.example.movieappkotlin.pojo.Myresponse
import com.example.movieappkotlin.pojo.ResultItem
import com.example.movieappkotlin.repository.HomeRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    var homeRepository: HomeRepository? = null
    var apiInterface: ApiInterface? = null

    var popularMutableLiveData: MutableLiveData<Myresponse?> = MutableLiveData<Myresponse?>()

    fun popularLiveData(): LiveData<Myresponse?>? {
        return popularMutableLiveData
    }

    fun getPopularMovie(context: Context?) {
        apiInterface = ApiServices().getINSTANCE(context)
        homeRepository = HomeRepository()

        homeRepository!!.popular(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .subscribe(object : Observer<Myresponse?> {
                override fun onSubscribe(d: @NonNull Disposable?) {}
                override fun onNext(myResponse: @NonNull Myresponse?) {
                    popularMutableLiveData.setValue(myResponse)

                     Log.e("getPopularMovie", "onResponse: " + myResponse!!.page)
                     Log.e("getPopularMovie", "onResponse: " + myResponse!!.total_pages)
                     Log.e("getPopularMovie", "onResponse: " + myResponse?.total_results)
                     Log.e("getPopularMovie", "onResponse: " + myResponse?.results)
                }
                override fun onError(e: @NonNull Throwable?) {
                    Log.e("getPopularMovie", "onError: " + e?.message)
                }
                override fun onComplete() {}
            })
    }
}