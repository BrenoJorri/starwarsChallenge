package com.trivago.core.result

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

data class Resource<out T>(val status: Status, val data: T?, val exception: Throwable?) {
    enum class Status { SUCCESS, ERROR, LOADING , EMPTY}

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable?): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> empty(data: T? = null): Resource<T> {
            return Resource(Status.EMPTY, data, null)
        }
    }
}

fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onLoading: () -> Unit,
    onEmpty: () -> Unit = {}
) {
    observe(owner, { resource ->
        when (resource.status) {
            Resource.Status.SUCCESS -> {
                resource.data?.let {
                    onSuccess.invoke(it)
                }
            }
            Resource.Status.ERROR -> {
                resource.exception?.let {
                    onError.invoke(it)
                }
            }
            Resource.Status.LOADING -> {
                onLoading.invoke()
            }
            Resource.Status.EMPTY -> {
                onEmpty.invoke()
            }
        }
    })
}
