package com.suitmedia.question1.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suitmedia.question1.data.Repository
import com.suitmedia.question1.ui.third.ThirdScreenViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ThirdScreenViewModel::class.java) -> {
                ThirdScreenViewModel(Injection.provideRepository(context)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
        }
    }
}