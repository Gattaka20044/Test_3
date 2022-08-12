package com.bignerdranch.android.test_3.persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.model.persons.Result

class PersonsViewModel : ViewModel() {
    val personsItemViewModel: LiveData<List<Result>>

    init {
        personsItemViewModel = PersonsFetch().fetchPersons()
    }
}