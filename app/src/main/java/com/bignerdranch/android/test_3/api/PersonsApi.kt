package com.bignerdranch.android.test_3.api


import com.bignerdranch.android.test_3.model.person.Person
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PersonsApi {
    @GET("character")
    fun fetchPersons(): Call<ResultsResponse>

    @GET
    fun funUrlBytes(@Url url: String): Call<ResponseBody>

    @GET
    fun fetchPerson(@Url url: String): Call<Person>
}