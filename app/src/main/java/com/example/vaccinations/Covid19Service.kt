package com.example.vaccinations

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Covid19Service {
    @GET("vaccine/coverage/countries")
    fun getVaccinations(@Query("lastdays") lastDays: Int): Call<List<VaccinationInfo>>

    @GET("countries")
    fun getCases(@Query("lastdays") lastDays: Int): Call<List<CovidCases>>

    // @GET("all")
    // fun(getWorldwideCases() : Call<WorldwideCases>
}