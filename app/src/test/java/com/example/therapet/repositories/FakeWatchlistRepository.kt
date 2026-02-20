import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.model.UserRole
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeWatchlistRepository : WatchlistRepositoryContract {

    private val watchlistFlow =
        MutableStateFlow<List<AccountUIModel>>(emptyList())

    override suspend fun addPatientToWatchlist(
        therapistId: String,
        patientId: String
    ) {
        val updated = watchlistFlow.value.toMutableList()

        updated.add(
            AccountUIModel(
                userid = patientId,
                fullName = "Test Patient",
                role = UserRole.THERAPIST,
                lastLoginMillis = null
            )
        )

        watchlistFlow.value = updated
    }

    override fun getWatchlistForTherapist(
        therapistId: String
    ): Flow<List<AccountUIModel>> {
        return watchlistFlow
    }

    override suspend fun removePatientFromWatchlist(
        therapistId: String,
        patientId: String
    ) {
        watchlistFlow.value =
            watchlistFlow.value.filterNot { it.userid == patientId }
    }
}