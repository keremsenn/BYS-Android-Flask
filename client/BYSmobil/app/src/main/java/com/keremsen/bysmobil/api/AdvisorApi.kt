package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.Advisor
import retrofit2.http.GET
import retrofit2.http.Query

interface AdvisorApi {
    @GET("advisor/id")
    suspend fun advisorGetById(@Query("id") id:Int):Advisor

    @GET("advisor/")
    suspend fun advisorGetAll():List<Advisor>
}