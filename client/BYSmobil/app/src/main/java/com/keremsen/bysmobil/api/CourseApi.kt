package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.Course
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApi {
    @GET("course/")
    suspend fun courseGetAll():List<Course>

    @GET("course/id")
    suspend fun courserGetById(@Query("id") id:Int):Course

    @GET("course/code")
    suspend fun courseGetByCourseCode(@Query("code") code:Int):Course


}