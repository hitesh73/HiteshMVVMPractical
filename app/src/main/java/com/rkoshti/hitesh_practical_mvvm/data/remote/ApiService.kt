package com.rkoshti.hitesh_practical_mvvm.data.remote

import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("ios-practical.json")
    suspend fun getUsers(): List<UserResponseItem>
}