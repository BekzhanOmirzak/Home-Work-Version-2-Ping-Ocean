package com.example.hometaskversion4mvp.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RingAppServiceProvider {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dev-api.ringapp.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun provideRingAppService() = retrofit.create(RingAppService::class.java);


}