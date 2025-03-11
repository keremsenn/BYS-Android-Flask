package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.StudentApi
import com.keremsen.bysmobil.model.Student
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentApi: StudentApi,
    private val context: Context
) {
    suspend fun studentGetAll():List<Student>{
        return studentApi.studentGetAll()
    }


    suspend fun studentGetById(id:Int): Student {
        return studentApi.studentGetById(id)
    }

    suspend fun studentGetByAdvisorId(advisorId:Int):List<Student>{
        return studentApi.studentGetByAdvisorId(advisorId)
    }

}