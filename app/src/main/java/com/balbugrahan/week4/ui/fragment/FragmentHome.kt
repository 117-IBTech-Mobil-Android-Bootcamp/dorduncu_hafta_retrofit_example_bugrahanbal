package com.balbugrahan.week4.ui.fragment

import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.balbugrahan.week4.R
import com.balbugrahan.week4.base.BaseFragment
import com.balbugrahan.week4.model.TaskData
import com.balbugrahan.week4.model.User
import com.balbugrahan.week4.response.AddTaskResponse
import com.balbugrahan.week4.response.GetAllTaskResponse
import com.balbugrahan.week4.service.BaseCallBack
import com.balbugrahan.week4.service.ServiceConnector
import com.balbugrahan.week4.utils.USER_TOKEN
import com.balbugrahan.week4.utils.getString
import com.balbugrahan.week4.utils.toast
import kotlinx.android.synthetic.main.dialog_add_task.*
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome : BaseFragment() {

    // private lateinit var adapter1: Adapter
    // private var layoutManager1 : GridLayoutManager? = null
    private var token : String?= null

    override fun getLayoutID() = R.layout.fragment_home


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //ADAPTER MANAGER
        adapter1 = Adapter(ImageData())

        //Recyclerview we are customing layout
        //layoutManager1 = GridLayoutManager(context, 1)
        //layoutManager1!!.orientation = LinearLayoutManager.VERTICAL

        //rcyler and layout setting
        //recyclerviewHome.layoutManager = layoutManager1
        //rcyler and adapter setting
        recyclerviewHome.adapter = adapter1

        floatingActionButton.setOnClickListener{
            addTaskMaterialDialog()
        }

    }


    fun addTaskMaterialDialog() {

        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_task, null)
        val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialogView).show()
        val editText:EditText =mDialogView.findViewById(R.id.editText_dialog_add)

        val descriptions = editText.getString()


        mBuilder.button_dialog_save.setOnClickListener {

            toast("clicklendi")

               val paramsOfTask = mutableMapOf<String, Any>().apply {
                   put("description", descriptions)

               }

            ServiceConnector.restInterface.addTask(paramsOfTask).enqueue(object : BaseCallBack<AddTaskResponse>() {


                   override fun onSuccess(response: AddTaskResponse) {
                       super.onSuccess(response)

                       toast("Task Prosses Succesfull")
                       //TaskData.getCurrentInstanceTask().setData(data)
                   }

                   override fun onFailure() {
                       super.onFailure()
                       toast("Eror for AddingTask")
                   }
               })

        }
        mBuilder.button_dialog_cancel.setOnClickListener {
                mBuilder.dismiss() }


    }

    fun getTasks(){
        ServiceConnector.restInterface.getAllTask(params).enqueue(object : BaseCallBack<GetAllTaskResponse>() {


            override fun onSuccess(response: AddTaskResponse) {
                super.onSuccess(response)

                toast("Task Prosses Succesfull")
                //TaskData.getCurrentInstanceTask().setData(data)
            }

            override fun onFailure() {
                super.onFailure()
                toast("Eror for AddingTask")
            }
        }

    }

    fun deleteTaskByID(){

        ServiceConnector.restInterface.deleteTaskByID(params).enqueue(object : BaseCallBack<GetAllTaskResponse>() {


            override fun onSuccess(response: AddTaskResponse) {
                super.onSuccess(response)

                toast("Task Prosses Succesfull")
                //TaskData.getCurrentInstanceTask().setData(data)
            }

            override fun onFailure() {
                super.onFailure()
                toast("Eror for AddingTask")
            }
        }


    }


}


