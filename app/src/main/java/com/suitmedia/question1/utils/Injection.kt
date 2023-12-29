package com.suitmedia.question1.utils

import android.content.Context
import com.suitmedia.question1.data.Repository
import com.suitmedia.question1.data.api.ApiConfig

object Injection {
    fun provideRepository(context: Context) : Repository {
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}