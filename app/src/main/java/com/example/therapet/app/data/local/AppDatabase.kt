package com.example.therapet.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.therapet.app.data.local.dao.UserDao
import com.example.therapet.app.data.entity.UserEntity
import com.example.therapet.app.data.local.dao.AppointmentDao
import com.example.therapet.app.data.entity.AppointmentEntity
import com.example.therapet.app.data.local.dao.WatchlistDao
import com.example.therapet.app.data.entity.WatchlistEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

/**
 * The main room database for the TheraPet application
 *
 * Defines all the entities and provides access to the DAO interfaces
 */
@Database(
    entities = [
        UserEntity::class,
        AppointmentEntity::class,
        WatchlistEntity::class
               ],
    version = 5,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the DAOs
     */
    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun watchlistDao(): WatchlistDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns the database instance and creates it if it doesnt already exist
         */
        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {

                SQLiteDatabase.loadLibs(context)

                val passphrase: ByteArray =
                    SQLiteDatabase.getBytes("therapet-secure-key".toCharArray())

                val factory = SupportFactory(passphrase)

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "therapet_db"
                )
                    /**
                     * Enables encryption
                     */
                    .openHelperFactory(factory)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
    }
}