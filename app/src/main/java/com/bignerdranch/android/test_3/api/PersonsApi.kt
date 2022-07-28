package com.bignerdranch.android.test_3.api

import retrofit2.Call
import retrofit2.http.GET

interface PersonsApi {
    @GET("character")
    fun fetchPersons(): Call<ResultsResponse>
}