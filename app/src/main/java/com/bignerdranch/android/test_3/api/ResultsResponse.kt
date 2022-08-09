package com.bignerdranch.android.test_3.api

import com.bignerdranch.android.test_3.model.Result
import com.google.gson.annotations.SerializedName

class ResultsResponse {
    @SerializedName("results")
    lateinit var personsItem: List<Result>
}