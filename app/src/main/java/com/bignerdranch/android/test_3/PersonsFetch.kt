package com.bignerdranch.android.test_3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.test_3.api.PersonsApi
import com.bignerdranch.android.test_3.model.Persons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PersonsFetch {

    private val personsApi: PersonsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        personsApi = retrofit.create(PersonsApi::class.java)
    }

    fun fetchPerson(page: Int): LiveData<Persons> {
        val responseLiveData: MutableLiveData<Persons> = MutableLiveData()
        val personRequest: Call<Persons> = personsApi.fetchPersons(page)

        personRequest.enqueue(object : Callback<Persons> {
            override fun onFailure(call: Call<Persons>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<Persons>,
                response: Response<Persons>
            ) {
                val personsResponse: Persons? = response.body()
                //val personItems: List<Result> = personsResponse?.personsItem ?: mutableListOf()

                responseLiveData.value = personsResponse
            }
        })
        return responseLiveData
    }
}