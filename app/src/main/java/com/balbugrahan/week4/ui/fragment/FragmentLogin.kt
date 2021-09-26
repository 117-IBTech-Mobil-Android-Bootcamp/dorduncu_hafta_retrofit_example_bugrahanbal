package com.balbugrahan.week4.ui.fragment


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import com.balbugrahan.week4.R
import com.balbugrahan.week4.base.BaseFragment
import com.balbugrahan.week4.response.LoginResponse
import com.balbugrahan.week4.service.BaseCallBack
import com.balbugrahan.week4.service.ServiceConnector
import com.balbugrahan.week4.utils.*
import kotlinx.android.synthetic.main.fragment_login.*


class FragmentLogin : BaseFragment() {

    //var registerResponseToken : RegisterResponse? =null

    override fun getLayoutID()=R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {

                hitLoginEndpoint()
        }

        button_register_login.setOnClickListener {
            navigateToNextFragment(R.id.action_fragmentLogin_to_fragmentRegister)
        }

    }

    private fun hitLoginEndpoint() {

        val email = emailLogin.getString()
        val password = passwordLogin.getString()

        if (allFieldsAreValid(email, password)) {

            val params = mutableMapOf<String, Any>().apply {
                put("email", email)
                put("password", password)
            }

            ServiceConnector.restInterface.userLogin(params).enqueue(object : BaseCallBack<LoginResponse>() {
                override fun onSuccess(loginResponse: LoginResponse) {
                    super.onSuccess(loginResponse)

                    Log.e("going user in ",loginResponse.token)
                    if (true) {
                        navigateToNextFragment(R.id.action_fragmentLogin_to_fragmentHome)
                        toast("Login Prosses Succesfull")
                    }else{
                        toast("ife giremedik la")
                    }
                }

                override fun onFailure() {
                    super.onFailure()
                    toast("Eror for Login")
                }
            })
        } else {
            toast("Please check your info.")
        }
    }


    private fun allFieldsAreValid(
        email: String,
        password: String
    ): Boolean {
        var allFieldsAreValid = true

        if (email.isEmpty() || !isValidEmail(email)) {
            allFieldsAreValid = false
        }
        if (password.isEmpty() || password.length < 7) allFieldsAreValid = false

        return allFieldsAreValid
    }

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}