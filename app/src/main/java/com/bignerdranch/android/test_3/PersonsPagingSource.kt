package com.bignerdranch.android.test_3

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bignerdranch.android.test_3.api.PersonsApi
import com.bignerdranch.android.test_3.model.Result
import java.lang.Exception

class PersonsPagingSource(private val loader: PersonsApi) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {


            val nextPage: Int = params.key ?: FIRST_PAGE
            val response = loader.fetchPersons(nextPage)

            var nextPageNumber: Int? = null
            if (response?.info?.next != null) {
                val uri = Uri.parse(response?.info?.next!!)
                nextPageNumber = uri?.getQueryParameter("page")?.toInt()
            }

            var pervPageNumber: Int? = null
            if (response?.info?.prev != null) {
                val uri = Uri.parse(response?.info?.prev!!)
                pervPageNumber = uri?.getQueryParameter("page")?.toInt()
            }
            Log.d("Load", "${response.info.count}")
            LoadResult.Page(data = response.results,
                prevKey = pervPageNumber,
                nextKey = nextPageNumber)

        } catch (e: Exception) {
            Log.e("Load", "Load error")
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int {
        return 1
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}
