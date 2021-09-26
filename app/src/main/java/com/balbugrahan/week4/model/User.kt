package com.balbugrahan.week4.model

import com.google.gson.annotations.SerializedName

class User( // register ve login responsdan dönen user data modeli
) {

    @SerializedName("name")  var userName: String?= null
    @SerializedName("age")   var age : Int ?= null
    @SerializedName("email") var email : String ?= null
    @SerializedName("token") var token : String ?= null
    //Response da token userdan ayrı obje

    companion object{
        var user : User ?= null

        fun getCurrentInstanceUser() : User {

            if(user == null){
                user = User()
            }
            return user!!
        }
    }

    fun setUser(registeredUser: User){
        user?.age = registeredUser.age
        user?.userName = registeredUser.userName
        user?.email = registeredUser.email
    }



}