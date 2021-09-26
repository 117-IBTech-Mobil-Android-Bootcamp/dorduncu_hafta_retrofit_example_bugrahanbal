package com.balbugrahan.week4.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balbugrahan.week4.R
import com.balbugrahan.week4.base.BaseFragment
import com.balbugrahan.week4.response.RegisterResponse
import com.balbugrahan.week4.service.BaseCallBack
import com.balbugrahan.week4.service.ServiceConnector
import com.balbugrahan.week4.utils.*
import kotlinx.android.synthetic.main.fragment_register.*


class FragmentRegister : BaseFragment() {

    override fun getLayoutID() = R.layout.fragment_register

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)


    }

    override fun prepareView() {

        button_register.setOnClickListener {
            hitRegisterEndpoint()
        }
    }


    private fun hitRegisterEndpoint() {

                    val email = emailET.getString()
                    val password = password.getString()
                    val name = nameET.getString()
                    val age = ageET.getString()

                    if (allFieldsAreValid(email, password,name,age)) {
                        // Make a request
                        progressbar.visible()

                        val params = mutableMapOf<String, Any>().apply {
                            put("name", name)
                            put("email", email)
                            put("password", password)
                            put("age", age)
            }

            ServiceConnector.restInterface.register(params).enqueue(object : BaseCallBack<RegisterResponse>() {
                override fun onSuccess(registerResponse: RegisterResponse) {
                    super.onSuccess(registerResponse)

                    progressbar.gone()
                    Log.e("crated user token ", registerResponse.token)
                    saveDataAsString(USER_TOKEN, registerResponse.token)
                }

                override fun onFailure() {
                    super.onFailure()
                    progressbar.gone()
                    toast("bir hata oluştu")
                }
            })
        } else {
            toast("Lütfen alanları kontrol ediniz.")
        }

    }

    private fun allFieldsAreValid(
            email: String,
            password: String, name: String, age: String
    ): Boolean {
        var allFieldsAreValid = true

        if (email.isEmpty() || !isValidEmail(email)) {
            allFieldsAreValid = false
        }

        if (password.isEmpty() || password.length < 7) allFieldsAreValid = false
        if (name.isEmpty() || name.length < 2) allFieldsAreValid = false
        if (age.isEmpty() || age.length < 2) allFieldsAreValid = false


        return allFieldsAreValid
    }



    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }




}