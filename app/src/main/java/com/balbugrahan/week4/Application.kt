package com.balbugrahan.week4

import androidx.multidex.MultiDexApplication
import com.balbugrahan.week4.service.ServiceConnector

class Application : MultiDexApplication(){

    companion object {
        private var instance: Application? = null

        fun getInstance(): Application? {
            return instance }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        ServiceConnector.init()

    }
}