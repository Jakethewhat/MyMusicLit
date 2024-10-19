package com.example.musiclstr

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(val username: String, val password: String)
data class LoginRequest(val username: String, val password: String)
data class ApiResponse(val message: String)




interface ApiService {
    @POST("register")
    fun register(@Body register: RegisterRequest): Call<ApiResponse>

    @POST("login")
    fun login(@Body login: LoginRequest): Call<ApiResponse>
}