package com.trivago.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trivago.core.result.Resource

abstract class BaseViewModel : ViewModel() {

    protected fun <T> MutableLiveData<Resource<T>>.success(data: T?) {
        postValue(Resource.success(data))
    }

    protected fun <T> MutableLiveData<Resource<T>>.error(e: Throwable?) {
        value = Resource.error(e)
    }

    protected fun <T> MutableLiveData<Resource<T>>.loading() {
        value = Resource.loading()
    }

    protected fun <T> MutableLiveData<Resource<T>>.empty(data: T? = null) {
        postValue(Resource.empty(data))
    }
}
