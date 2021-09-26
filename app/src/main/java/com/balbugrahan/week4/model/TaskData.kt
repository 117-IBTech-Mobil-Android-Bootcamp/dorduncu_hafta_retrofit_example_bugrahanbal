package com.balbugrahan.week4.model

import com.google.gson.annotations.SerializedName

public class TaskData ( // task responsedan dönen data icin model
) {

    @SerializedName("success") var success: Boolean = true
    @SerializedName("completed") var completed : Boolean =false
    @SerializedName("_id") var _id : String? = null
    @SerializedName("description") var description: String?= null

    companion object{
        var taskData :TaskData ?= null

        fun getCurrentInstanceTask() : TaskData {

            if(taskData == null){
                taskData = TaskData()
            }
            return taskData!!
        }
    }

    fun setData(addedData: TaskData){
        taskData?. success= addedData.success
        taskData?.completed = addedData.completed // comple
        //taskData?. _id= addedData._id //idyi kendi atıyor
        taskData?.description = addedData.description



    }



}
