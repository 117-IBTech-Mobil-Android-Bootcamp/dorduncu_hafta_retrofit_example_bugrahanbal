package com.balbugrahan.week4.service

import com.balbugrahan.week4.model.TaskData
import com.balbugrahan.week4.model.User
import com.balbugrahan.week4.response.LoginResponse
import com.balbugrahan.week4.response.RegisterResponse
import com.balbugrahan.week4.response.AddTaskResponse
import com.balbugrahan.week4.response.GetAllTaskResponse
import retrofit2.Call
import retrofit2.http.*

interface TodoAPI {


    // USER //
    @POST("user/register") // Register User
    fun register(@Body params : MutableMap<String, Any>) : Call<RegisterResponse>

    @GET("user/me") //Get Logged In User via Token
    fun getMe() : Call<User>

    @POST("user/login") //Login
    fun userLogin(@Body params: MutableMap<String, Any>): Call<LoginResponse>

    // TASK //
    @POST("/task") // Add Task
    fun addTask(@Body params: MutableMap<String, Any>): Call<AddTaskResponse>

    @GET("/task") // Get All Task via Token
    fun getAllTask(): Call<TaskData>

    @DELETE("task/{id}")
    fun deleteTaskByID(@Path("id") id: String): Call<GetAllTaskResponse>
}