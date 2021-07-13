package com.example.movieappkotlin.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Myresponse (

    @SerializedName("page")
     var page:Int=0,
    @SerializedName("total_results")
     var total_results:Int = 0,
    @SerializedName("total_pages")
     var total_pages:Int = 0,
    @SerializedName("results")
    var results:ArrayList<ResultItem>,
    var data: HashMap<Object , Object>

):Serializable