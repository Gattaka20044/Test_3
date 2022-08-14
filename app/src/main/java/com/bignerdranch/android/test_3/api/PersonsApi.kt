package com.bignerdranch.android.test_3.api

import com.bignerdranch.android.test_3.model.Persons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonsApi {
    @GET("character")
    fun fetchPersons(@Query("page") query: Int): Call<Persons>
}