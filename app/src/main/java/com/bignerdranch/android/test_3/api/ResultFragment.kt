package com.bignerdranch.android.test_3.api

import com.bignerdranch.android.test_3.model.person.Origin
import com.bignerdranch.android.test_3.model.persons.Result
import com.google.gson.annotations.SerializedName

class ResultFragment {
    @SerializedName("origin")
    lateinit var personItem: Origin
}
