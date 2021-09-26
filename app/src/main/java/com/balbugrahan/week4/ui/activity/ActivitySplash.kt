package com.balbugrahan.week4.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.balbugrahan.week4.R
import com.balbugrahan.week4.model.User
import com.balbugrahan.week4.service.BaseCallBack
import com.balbugrahan.week4.service.ServiceConnector
import com.balbugrahan.week4.utils.USER_TOKEN
import com.balbugrahan.week4.utils.startActivity
import java.util.*


class ActivitySplash : AppCompatActivity() {

    private val DELAY  :  Long = 3 * 1000 //Splash delay time


    private var token : String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        /*Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity<ActivityMain>{
                }
            }
        }, DELAY) }*/

        if(isLoggedIn()){

            //HOMEPAGE SHOULD BE SEEN

            User.getCurrentInstanceUser().token=token
            ServiceConnector.restInterface.getMe().enqueue(object : BaseCallBack<User>(){
                override fun onSuccess(data: User) {
                    super.onSuccess(data)
                   // progressbar.gone()
                    User.getCurrentInstanceUser().setUser(data)
                //toast("User is logged in successfully")
                }

                override fun onFailure() {
                    super.onFailure()
                    Log.e("something went", "wrong")

                }
            })

        }else{

            //REGISTER SCREEN SHOULD BE SEEN
         startActivity(Intent(this, ActivityMain::class.java))
        }
    }

    private fun isLoggedIn() : Boolean{
        val token = getToken()
        return if(token.isEmpty()) false else true
    }

    private fun getToken() : String{
        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        token = sh.getString(USER_TOKEN, "")
        return token!!
    }

}