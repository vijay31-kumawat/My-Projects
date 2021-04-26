package com.example.demo.data.remote

import com.example.demo.pojo.ImageData
import com.example.demo.utils.Config
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET(Config.LIST) //--------to get all photos----
    suspend fun getMyPhotos(@Query("page") page: Int?, ):List<ImageData>

}