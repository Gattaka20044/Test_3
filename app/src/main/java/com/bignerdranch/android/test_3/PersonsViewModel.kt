package com.bignerdranch.android.test_3

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.model.persons.Result

class PersonsViewModel : ViewModel() {
    val personsItemViewModel: LiveData<List<Result>>

    init {
        personsItemViewModel = PersonsFetch().fetchPerson()
    }
}