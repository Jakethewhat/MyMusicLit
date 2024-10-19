package com.example.musiclstr

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun registerUser(username: String, password: String) {
    val request = RegisterRequest(username, password)
    RetrofitClient.instance.register(request).enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                // Handle successful registration
                val responseBody = response.body()
                responseBody?.let {
                    println(it.message)
                }
            } else {
                // Handle error
                println("Error: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            // Handle failure
            println("Error: ${t.message}")
        }
    })
}
