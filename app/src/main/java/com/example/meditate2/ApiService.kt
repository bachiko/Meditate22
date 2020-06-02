package com.example.meditate2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("5ed54526330000a72df7a92b")
    fun fetchPhotos() : Call<List<PhotoModel>>
}