package com.example.therapet.ui.viewmodels
/**
import FakeWatchlistRepository
import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.ui.viewmodel.WatchlistViewModel
import com.example.therapet.helpers.FakeSessionManager
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
class WatchlistViewModelTest {

    private lateinit var repository: FakeWatchlistRepository
    private lateinit var sessionManager: FakeSessionManager
    private lateinit var viewModel: WatchlistViewModel

    @Before
    fun setup() {
        repository = FakeWatchlistRepository()
        sessionManager = FakeSessionManager()
        sessionManager.userId = "therapist1"

        viewModel = WatchlistViewModel(repository, sessionManager)
    }

    @Test
    fun watchlist_updates_whenRepositoryEmits() = runTest {

        val list = listOf(
            AccountUIModel(
                userid = "p1",
                fullName = "Jane Doe",
                role = UserRole.PATIENT
            )
        )

        repository.emitWatchlist(list)

        advanceUntilIdle()

        assertEquals(1, viewModel.watchlist.value.size)
        assertEquals("Jane Doe", viewModel.watchlist.value[0].fullName)
    }

    @Test
    fun watchlist_notCollected_ifUserIdNull() = runTest {

        sessionManager.userId = null

        viewModel = WatchlistViewModel(repository, sessionManager)

        repository.emitWatchlist(
            listOf(AccountUIModel("p1", "Jane Doe", UserRole.PATIENT))
        )

        advanceUntilIdle()

        assertTrue(viewModel.watchlist.value.isEmpty())
    }
}**/