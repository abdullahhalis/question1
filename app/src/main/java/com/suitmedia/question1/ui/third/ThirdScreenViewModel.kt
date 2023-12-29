package com.suitmedia.question1.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.question1.data.Repository
import com.suitmedia.question1.data.response.DataItem

class ThirdScreenViewModel(repository: Repository) : ViewModel() {
    val users: LiveData<PagingData<DataItem>> = repository.getUsers().cachedIn(viewModelScope)
}