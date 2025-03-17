package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.StudentCourseSelectionApi
import com.keremsen.bysmobil.model.ApproveRequest
import com.keremsen.bysmobil.model.CourseSelectionAdd
import com.keremsen.bysmobil.model.StudentCourseSelection
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

class StudentCourseSelectionRepository @Inject constructor(
    private val studentCourseSelectionApi: StudentCourseSelectionApi,
    private val context: Context
) {

    suspend fun courseSelectionGetById(id:Int): StudentCourseSelection{
        return studentCourseSelectionApi.courseSelectionGetById(id)
    }
    suspend fun courseSelectionGetByStudentId(studentId:Int): List<StudentCourseSelection>{
        return studentCourseSelectionApi.courseSelectionGetByStudentId(studentId)
    }
    suspend fun courseSelectionGetByCoursetId( courseId:Int): List<StudentCourseSelection>{
        return studentCourseSelectionApi.courseSelectionGetByCoursetId(courseId)
    }
    suspend fun courseSelectionUpdateApprove( aproveRequest: ApproveRequest): StudentCourseSelection{
        return studentCourseSelectionApi.courseSelectionUpdateApprove(aproveRequest)
    }
    suspend fun courseSelectionAdd(courseSelectionAdd: CourseSelectionAdd){
         studentCourseSelectionApi.courseSelectionAdd(courseSelectionAdd)
    }
    suspend fun courseSelectionDelete(id:Int){
        studentCourseSelectionApi.courseSelectionDelete(id)
    }
}