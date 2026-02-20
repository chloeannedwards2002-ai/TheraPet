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

    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun watchlistDao(): WatchlistDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "therapet_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
    }
}