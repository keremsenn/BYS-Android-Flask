package com.keremsen.bysmobil.api

import com.keremsen.bysmobil.model.Transcript
import retrofit2.http.GET
import retrofit2.http.Query

interface TranscriptApi {

    @GET("transcript/id")
    suspend fun transcriptGetByStudentId(@Query("studentId") studentId:Int ):Transcript
}