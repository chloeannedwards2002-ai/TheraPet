package com.example.therapet.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import com.example.therapet.app.data.session.SessionManagerContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Handles list of patients that a therapist is monitoring
 */
class WatchlistViewModel(
    private val watchlistRepository: WatchlistRepositoryContract,
    private val sessionManager: SessionManagerContract
) : ViewModel() {

    val watchlist = MutableStateFlow<List<AccountUIModel>>(emptyList())

    /**
     * Collect updates from repo and update StateFlow
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = sessionManager.getUserId() ?: return@launch
            watchlistRepository.getWatchlistForTherapist(userId).collect { list ->
                watchlist.value = list
            }
        }
    }
}