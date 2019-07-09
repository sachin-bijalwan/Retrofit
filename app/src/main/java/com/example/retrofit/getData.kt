package com.example.retrofit

import io.reactivex.Observable
import retrofit2.http.GET

interface getData{
    @GET("comments")
    fun getData() : Observable<List<comments>>

}