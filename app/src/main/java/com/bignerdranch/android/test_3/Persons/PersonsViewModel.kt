package com.bignerdranch.android.test_3.Persons


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bignerdranch.android.test_3.PersonsPagingSource
import com.bignerdranch.android.test_3.api.PersonsApi
import com.bignerdranch.android.test_3.api.RetrofitInstance
import com.bignerdranch.android.test_3.model.Persons.Result
import kotlinx.coroutines.flow.Flow


class PersonsViewModel : ViewModel() {

    val personApi: PersonsApi

    init {
        personApi = RetrofitInstance.getRetrofitInstance().create(PersonsApi::class.java)
    }

    fun getListData(): Flow<PagingData<Result>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { PersonsPagingSource(personApi) }).flow.cachedIn(viewModelScope)

    }
}