package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.UserStudentApi
import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserStudent
import javax.inject.Inject

class UserStudentRepository @Inject constructor(
    private val userStudentApi : UserStudentApi,
    private val context : Context
) {

    suspend fun login(loginRequest: LoginRequest): UserStudent?{
        return userStudentApi.login(loginRequest)
    }

}