package com.balbugrahan.week4.response

import com.balbugrahan.week4.model.User
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("user") val user: User, //email and password
    @SerializedName("token") val token : String // token
)