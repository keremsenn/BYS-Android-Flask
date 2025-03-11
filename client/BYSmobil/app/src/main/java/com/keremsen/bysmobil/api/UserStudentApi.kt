package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserStudent
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserStudentApi {

    @POST("user-student/login")
    suspend fun login(@Body loginRequest: LoginRequest): UserStudent

}