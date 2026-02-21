import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.therapet.app.data.datastore.PetPreferencesKeys
import com.example.therapet.app.data.repository.contracts.PetPreferencesRepositoryContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Implementation of PetPreferencesRepositoryContract
 *
 * Manages persistent pet preferences by using DataStore
 */
class PetPreferencesRepository private constructor(
    private val dataStore: DataStore<Preferences>
) : PetPreferencesRepositoryContract {

    companion object {
        private val instances = mutableMapOf<String, PetPreferencesRepository>()

        fun getInstance(context: Context, userId: String): PetPreferencesRepository {
            return instances.getOrPut(userId) {
                PetPreferencesRepository(
                    PreferenceDataStoreFactory.create(
                        produceFile = { context.preferencesDataStoreFile("pet_preferences_$userId") }
                    )
                )
            }
        }
    }

    /**
     * Current selected pet colour index
     */
    override val petColourIndex: Flow<Int> = dataStore.data.map { prefs -> prefs[PetPreferencesKeys.PET_COLOUR_INDEX] ?: 0 }

    /**
     * Saves new pet colour index to the DataStore
     *
     * @param index the selected colour index
     */
    override suspend fun savePetColour(index: Int) {
        dataStore.edit { prefs ->
            prefs[PetPreferencesKeys.PET_COLOUR_INDEX] = index
        }
    }
}