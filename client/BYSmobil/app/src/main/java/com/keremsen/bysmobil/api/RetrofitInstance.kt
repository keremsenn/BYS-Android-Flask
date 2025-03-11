package com.keremsen.bysmobil.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:5000/"


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userStudentApi: UserStudentApi by lazy {
        retrofit.create(UserStudentApi::class.java)
    }
    val userAdvisorApi: UserAdvisorApi by lazy {
        retrofit.create(UserAdvisorApi::class.java)
    }

    val studentApi:StudentApi by lazy{
        retrofit.create(StudentApi::class.java)
    }
    val advisorApi:AdvisorApi by lazy {
        retrofit.create(AdvisorApi::class.java)
    }
    val courseApi:CourseApi by lazy {
        retrofit.create(CourseApi::class.java)
    }
    val studentCourseSelectionApi:StudentCourseSelectionApi by lazy {
        retrofit.create(StudentCourseSelectionApi::class.java)
    }
    val transcriptApi:TranscriptApi by lazy {
        retrofit.create(TranscriptApi::class.java)
    }

}