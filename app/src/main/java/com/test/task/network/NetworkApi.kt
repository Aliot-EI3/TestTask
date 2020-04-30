package com.test.task.network

import com.test.task.model.Image
import io.reactivex.Observable
import retrofit2.http.GET

interface NetworkApi {

    @GET("photos")
    fun getImageList(): Observable<List<Image>>

}