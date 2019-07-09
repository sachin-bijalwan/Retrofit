package com.example.retrofit

import android.database.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class comments(
//    "postId": 1,
//    "id": 1,
//    "name": "id labore ex et quam laborum",
//    "email": "Eliseo@gardner.biz",
//    "body": "laudantium eni
    var postId : Int,
    var id : Int,
            var name : String,
                    var email: String,
                            var body: String

)
