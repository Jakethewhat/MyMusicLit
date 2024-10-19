package com.example.musiclstr

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object RetrofitClient {
    private const val BASE_URL = "http://127.0.0.1:8000/api"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    interface ApiService {
        @POST("/register")  // Registration endpoint
        fun register(@Body registerRequest: RegisterRequest): Call<ApiResponse>

        @POST("/login")  // Login endpoint
        fun login(@Body loginRequest: LoginRequest): Call<ApiResponse>
    }
}