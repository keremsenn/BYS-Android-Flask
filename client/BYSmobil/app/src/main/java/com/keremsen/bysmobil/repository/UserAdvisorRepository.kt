package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.UserAdvisorApi
import com.keremsen.bysmobil.model.LoginRequest
import com.keremsen.bysmobil.model.UserAdvisor
import javax.inject.Inject

class UserAdvisorRepository @Inject constructor(
    private val userAdvisorApi: UserAdvisorApi,
    private  val context: Context
){
    suspend fun AdvisorLogin(loginRequest: LoginRequest):UserAdvisor? {
        return userAdvisorApi.Advisorlogin(loginRequest)
    }
}