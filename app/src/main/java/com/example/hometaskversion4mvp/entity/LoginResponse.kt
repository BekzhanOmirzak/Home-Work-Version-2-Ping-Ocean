package com.example.hometaskversion4mvp.entity

import com.google.gson.annotations.SerializedName


class LoginResponse {

    @SerializedName("token")
    val token: String = "";

    @SerializedName("error")
    val error: String = "";

}