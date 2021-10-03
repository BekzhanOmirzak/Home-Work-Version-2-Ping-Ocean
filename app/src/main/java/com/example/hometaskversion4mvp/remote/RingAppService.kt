package com.example.hometaskversion4mvp.remote

import com.example.hometaskversion4mvp.entity.LoginResponse
import com.example.hometaskversion4mvp.entity.PersonalProfileInf
import com.example.hometaskversion4mvp.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface RingAppService {


    @POST("auth")
    suspend fun authenticate(@Body user: User): Response<LoginResponse>

    @GET("profile")
    suspend fun getPersonalProfileInformation(@Header("Authorization") authHeader: String): Response<PersonalProfileInf>


}