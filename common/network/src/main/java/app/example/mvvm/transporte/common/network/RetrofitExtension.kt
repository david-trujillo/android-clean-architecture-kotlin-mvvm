package app.example.mvvm.transporte.common.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.make(onResponse: (ApiResult<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.code() != 200) {
                onResponse(ApiResult.Error(response.message()))
            } else {
                onResponse(ApiResult.Success(response.body()))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onResponse(ApiResult.Error(t.message ?: "Error"))
        }
    })
}