package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.CourseApi
import com.keremsen.bysmobil.model.Course
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val courseApi: CourseApi,
    private val context: Context
) {

    suspend fun courseGetAll(): List<Course>{
        return courseApi.courseGetAll()
    }


    suspend fun courserGetById( id:Int): Course{
        return courseApi.courserGetById(id)
    }


    suspend fun courseGetByCourseCode( code:Int): Course{
        return courseApi.courseGetByCourseCode(code)
    }
}