package com.suitmedia.question1.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.suitmedia.question1.data.api.ApiService
import com.suitmedia.question1.data.paging.UserPagingSource
import com.suitmedia.question1.data.response.DataItem

class Repository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}