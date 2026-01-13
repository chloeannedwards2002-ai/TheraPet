package com.example.therapet.app.ui.viewmodel

import PetPreferencesRepository
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.therapet.app.data.local.AppDatabase
import com.example.therapet.app.data.repository.contracts.PetPreferencesRepositoryContract
import com.example.therapet.app.data.repository.UserRepository
import com.example.therapet.app.data.repository.contracts.UserRepositoryContract
import com.example.therapet.app.data.session.SessionManager

/**
 * @author: Chloe Edwards
 * @date: 06/01/2026
 *
 * Manually instructs android how to construct the ViewModels
 */

// Nesting
class ViewModelFactory {

    class UserViewModelFactory(
        context: Context,
        private val sessionManager: SessionManager
    ) : ViewModelProvider.Factory {

        private val repository: UserRepositoryContract =
            UserRepository(
                AppDatabase.getDatabase(context).userDao()
            )

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(
                    repository = repository,
                    sessionManager = sessionManager
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class PetViewModelFactory(
        private val context: Context,
        private val userId: String
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repository = PetPreferencesRepository.getInstance(context, userId)

            if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PetViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
