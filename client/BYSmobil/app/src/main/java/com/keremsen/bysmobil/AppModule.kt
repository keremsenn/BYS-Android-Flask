package com.keremsen.bysmobil

import android.app.Application
import android.content.Context
import com.keremsen.bysmobil.api.AdvisorApi
import com.keremsen.bysmobil.api.CourseApi
import com.keremsen.bysmobil.api.RetrofitInstance
import com.keremsen.bysmobil.api.StudentApi
import com.keremsen.bysmobil.api.StudentCourseSelectionApi
import com.keremsen.bysmobil.api.TranscriptApi
import com.keremsen.bysmobil.api.UserAdvisorApi
import com.keremsen.bysmobil.api.UserStudentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideContext(app: Application): Context{
        return app.applicationContext
    }

    @Provides
    fun provideUserStudentApi(): UserStudentApi{
        return RetrofitInstance.userStudentApi
    }
    @Provides
    fun provideStudentApi(): StudentApi {
        return RetrofitInstance.studentApi
    }
    @Provides
    fun providesUserAdvisorApi():UserAdvisorApi{
        return RetrofitInstance.userAdvisorApi
    }
    @Provides
    fun providesAdvisorApi():AdvisorApi{
        return RetrofitInstance.advisorApi
    }
    @Provides
    fun providesCourseApi():CourseApi{
        return RetrofitInstance.courseApi
    }
    @Provides
    fun providesStudentCourseSelectionApi():StudentCourseSelectionApi{
        return RetrofitInstance.studentCourseSelectionApi
    }
    @Provides
    fun transcriptApi():TranscriptApi{
        return RetrofitInstance.transcriptApi
    }
}