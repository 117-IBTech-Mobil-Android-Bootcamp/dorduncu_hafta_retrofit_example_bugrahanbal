package com.balbugrahan.week4.response

import com.balbugrahan.week4.model.TaskData
import com.google.gson.annotations.SerializedName

data class GetAllTaskResponse (
        @SerializedName("data") val data: TaskData,
        @SerializedName("count") val count: Int
)