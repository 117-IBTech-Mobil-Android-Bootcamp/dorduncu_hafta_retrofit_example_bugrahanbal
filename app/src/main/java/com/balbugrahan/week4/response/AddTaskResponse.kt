package com.balbugrahan.week4.response

import com.balbugrahan.week4.model.TaskData
import com.google.gson.annotations.SerializedName

data class AddTaskResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("data") val data: TaskData
)