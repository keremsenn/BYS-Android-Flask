package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.ApproveRequest
import com.keremsen.bysmobil.model.StudentCourseSelection
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentCourseSelectionApi {
    @GET("selection/id")
    suspend fun courseSelectionGetById(@Query("id") id:Int):StudentCourseSelection

    @GET("selection/student_id")
    suspend fun courseSelectionGetByStudentId(@Query("studentId") studentId:Int):List<StudentCourseSelection>

    @GET("selection/course_id")
    suspend fun courseSelectionGetByCoursetId(@Query("courseId") courseId:Int):List<StudentCourseSelection>

    @POST("selection/update_approve")
    suspend fun courseSelectionUpdateApprove(@Body aproveRequest: ApproveRequest):StudentCourseSelection

}