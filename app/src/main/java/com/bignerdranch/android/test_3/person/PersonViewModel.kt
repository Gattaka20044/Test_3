package com.bignerdranch.android.test_3.person

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.api.PersonsApi
import com.bignerdranch.android.test_3.api.RetrofitInstance
import com.bignerdranch.android.test_3.model.Person.Person


class PersonViewModel: ViewModel() {

    val personApi: PersonsApi

    init {
        personApi = RetrofitInstance.getRetrofitInstance().create(PersonsApi::class.java)
    }

    suspend fun getItemPerson(id: Int): Person {
        val person = personApi.fetchPerson(id)
        return person
    }
}