package com.khurram.score.network

import java.lang.Exception


data class Resource<out T>(val status: Status, val data: T?, val message: String?, val exception: Exception?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null,exception = null)

        fun <T> error(data: T?, message: String,exception: Exception?): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message,exception = exception)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null,exception = null)
    }
}