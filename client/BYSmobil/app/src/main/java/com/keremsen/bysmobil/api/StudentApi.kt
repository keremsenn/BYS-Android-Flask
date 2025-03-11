package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.Student
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentApi {

    @GET("student/")
    suspend fun studentGetAll():List<Student>

    @GET("student/id")
    suspend fun studentGetById(@Query("id") id: Int): Student

    @GET("student/advisor")
    suspend fun studentGetByAdvisorId(@Query("advisor-id")id:Int):List<Student>


}