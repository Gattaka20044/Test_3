package com.bignerdranch.android.test_3.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.persons.PersonsViewModel

open class PersonViewModel : ViewModel() {
    val personItemViewModel: LiveData<Person>
    private val personsFetch = PersonsFetch()
    val urlPersonLiveData = MutableLiveData<String>()

    init {

        personItemViewModel = Transformations.switchMap(urlPersonLiveData) {
            personsFetch.fetchPerson(it)
        }

    }

    fun fetchUrlPerson(url: String) {
        urlPersonLiveData.value = url
    }
}