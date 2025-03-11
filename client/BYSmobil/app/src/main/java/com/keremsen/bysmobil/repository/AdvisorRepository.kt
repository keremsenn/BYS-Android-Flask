package com.keremsen.bysmobil.repository

import android.content.Context
import com.keremsen.bysmobil.api.AdvisorApi
import com.keremsen.bysmobil.model.Advisor
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class AdvisorRepository @Inject constructor(
    private val advisorApi: AdvisorApi,
    private val context: Context
) {

    suspend fun advisorGetById(id:Int): Advisor{
        return advisorApi.advisorGetById(id)
    }


    suspend fun advisorGetAll():List<Advisor>{
        return advisorApi.advisorGetAll()
    }

}