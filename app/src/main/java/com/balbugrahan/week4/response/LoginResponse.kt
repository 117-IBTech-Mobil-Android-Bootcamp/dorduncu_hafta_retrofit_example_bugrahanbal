package com.balbugrahan.week4.response

import com.balbugrahan.week4.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("user") val user: User, //email and password
        @SerializedName("token") val token : String // token
)