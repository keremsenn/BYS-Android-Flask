package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.ApproveRequest
import com.keremsen.bysmobil.model.CourseSelectionAdd
import com.keremsen.bysmobil.model.StudentCourseSelection
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StudentCourseSelectionApi {
    @GET("selection/id")
    suspend fun courseSelectionGetById(@Query("id") id:Int):StudentCourseSelection

    @GET("selection/student_id")
    suspend fun courseSelectionGetByStudentId(@Query("id") studentId:Int):List<StudentCourseSelection>

    @GET("selection/course_id")
    suspend fun courseSelectionGetByCoursetId(@Query("id") courseId:Int):List<StudentCourseSelection>

    @POST("selection/update_approve")
    suspend fun courseSelectionUpdateApprove(@Body aproveRequest: ApproveRequest):StudentCourseSelection

    @POST("selection/add")
    suspend fun courseSelectionAdd(@Body courseSelectionAdd: CourseSelectionAdd)

    @DELETE("selection/delete")
    suspend fun courseSelectionDelete(@Query("id") id:Int)

}