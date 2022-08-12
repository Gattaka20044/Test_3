package com.bignerdranch.android.test_3.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.model.person.Origin
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.model.persons.Result
import com.bignerdranch.android.test_3.persons.PersonsFragment

class PersonViewModel: ViewModel() {
    val personItemViewModel: LiveData<Person>
    lateinit var personItem: Result
    private val personsFetch = PersonsFetch()
    private val personFragment = PersonFragment()

    init {
      personItemViewModel = PersonsFetch().fetchPerson("https://rickandmortyapi.com/api/character/2\")
    }



}