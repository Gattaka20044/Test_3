package com.bignerdranch.android.test_3.api

import com.bignerdranch.android.test_3.model.Person.Person
import com.bignerdranch.android.test_3.model.Persons.Persons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonsApi {
    @GET("character")
    suspend fun fetchPersons(@Query("page") query: Int): Persons

    @GET("character/{id}")
    suspend fun fetchPerson(@Path("id") query: Int): Person
}