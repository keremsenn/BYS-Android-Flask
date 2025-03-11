package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserAdvisor
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAdvisorApi {
    @POST("user-advisor/login")
    suspend fun Advisorlogin(@Body loginRequest: LoginRequest):UserAdvisor
}