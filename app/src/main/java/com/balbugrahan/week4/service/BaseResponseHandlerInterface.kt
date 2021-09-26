package com.balbugrahan.week4.service

interface BaseResponseHandlerInterface<T> {

    fun onSuccess(data :  T)
    fun onFailure()
}