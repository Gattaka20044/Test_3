package com.bignerdranch.android.test_3.Persons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.model.Persons
import com.bignerdranch.android.test_3.model.Result

class PersonsViewModel : ViewModel() {
    val personsItemViewModel: LiveData<Persons>

    init {
        personsItemViewModel = PersonsFetch().fetchPerson(2)
    }
}