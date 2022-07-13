package com.hwangblood.rickandmorty.network

import retrofit2.Response

class NetworkResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    companion object {
        fun <T> success(data: Response<T>): NetworkResponse<T> {
            return NetworkResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception?): NetworkResponse<T> {
            return NetworkResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }

    val isFailed: Boolean
        get() = status == Status.Failure

    val isSuccessful: Boolean
        get() = !isFailed && data!!.isSuccessful

    val body: T
        get() = data?.body()!!
}