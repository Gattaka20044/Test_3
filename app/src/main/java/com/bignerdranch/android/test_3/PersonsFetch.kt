package com.bignerdranch.android.test_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.test_3.api.PersonsApi
import com.bignerdranch.android.test_3.api.ResultFragment
import com.bignerdranch.android.test_3.api.ResultsResponse
import com.bignerdranch.android.test_3.model.person.Origin
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.model.persons.Result
import com.bignerdranch.android.test_3.person.PersonFragment
import okhttp3.ResponseBody
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

    fun fetchPersons(): LiveData<List<Result>> {
        val responseLiveData: MutableLiveData<List<Result>> = MutableLiveData()
        val personRequest: Call<ResultsResponse> = personsApi.fetchPersons()

        personRequest.enqueue(object : Callback<ResultsResponse> {

            override fun onResponse(
                call: Call<ResultsResponse>,
                response: Response<ResultsResponse>
            ) {
                val personsResponse: ResultsResponse? = response.body()
                val personItems: List<Result> = personsResponse?.personsItem ?: mutableListOf()

                responseLiveData.value = personItems
            }

            override fun onFailure(call: Call<ResultsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return responseLiveData
    }

    @WorkerThread
    fun fetchAvatar(url: String): Bitmap? {
        val response: Response<ResponseBody> = personsApi.funUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)

        return bitmap

    }

    fun fetchPerson(url: String): LiveData<Person> {
        val responseLiveData: MutableLiveData<Person> = MutableLiveData()
        val personRequest: Call<Person> = personsApi.fetchPerson(url)

        personRequest.enqueue(object : Callback<Person> {

            override fun onResponse(
                call: Call<Person>,
                response: Response<Person>
            ) {
                val personResponse: Person? = response.body()
                responseLiveData.value = personResponse
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        return responseLiveData
    }
}