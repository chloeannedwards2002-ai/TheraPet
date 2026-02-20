import com.example.therapet.app.data.model.AccountUIModel
import com.example.therapet.app.data.repository.contracts.WatchlistRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeWatchlistRepository : WatchlistRepositoryContract {

    val addedPairs = mutableListOf<Pair<String, String>>()

    private val watchlistFlow = MutableStateFlow<List<AccountUIModel>>(emptyList())

    override suspend fun addPatientToWatchlist(
        therapistId: String,
        patientId: String
    ) {
        addedPairs.add(therapistId to patientId)
    }

    override fun getWatchlistForTherapist(
        therapistId: String
    ): Flow<List<AccountUIModel>> {
        return watchlistFlow
    }

}