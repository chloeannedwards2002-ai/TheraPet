package com.example.therapet.app.ui.viewmodel

import android.content.Context
import androidx.compose.ui.tooling.data.ContextCache
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.repository.UserRepository

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * Manually instructs android how to construct the ViewModel -
 */

// Nesting
class ViewModelFactory {

    class userViewModelFactory(
        // Class receives a context, implements ViewModelProvider and will be passed to viewModels {}
        context: Context

    ): ViewModelProvider.Factory {

        // getDatabase returns the Room database
        private val repository: UserRepository =
            UserRepository(
                // userDao() gives access to the database
                AppDatabase.getDatabase(context).userDao()
            )

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // Checks whic view model is requested
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repository) as T
            }
            //Fall back
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}