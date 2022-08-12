package com.bignerdranch.android.test_3.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.persons.PersonsFragment

class PersonViewModel: ViewModel() {
    val personItemViewModel: LiveData<Person>
    val personFragment = PersonFragment()

    init {
      personItemViewModel = PersonsFetch().fetchPerson(personFragment.getUrlPerson())
    }
}