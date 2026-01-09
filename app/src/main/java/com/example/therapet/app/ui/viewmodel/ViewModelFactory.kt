package com.example.therapet.app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.repository.UserRepository
import com.example.therapet.app.data.session.SessionManager

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * Manually instructs android how to construct the ViewModel -
 */

// Nesting
class ViewModelFactory {

    class UserViewModelFactory(
        // Class receives a context, implements ViewModelProvider and will be passed to viewModels {}
        context: Context

    ): ViewModelProvider.Factory {

        // getDatabase returns the Room database
        private val sessionManager = SessionManager()

        private val repository: UserRepository =
            UserRepository(
                AppDatabase.getDatabase(context).userDao()
            )

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // Checks whic view model is requested
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(
                    repository = repository,
                    sessionManager = sessionManager
                ) as T
            }
            //Fall back
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}