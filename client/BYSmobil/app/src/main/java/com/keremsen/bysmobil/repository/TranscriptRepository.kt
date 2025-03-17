package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.TranscriptApi
import com.keremsen.bysmobil.model.Transcript
import javax.inject.Inject

class TranscriptRepository @Inject constructor(
    private val transcriptApi: TranscriptApi,
    private val context: Context
) {

    suspend fun transcriptGetByStudentId( studentId:Int ): List<Transcript>{
        return transcriptApi.transcriptGetByStudentId(studentId)
    }
}