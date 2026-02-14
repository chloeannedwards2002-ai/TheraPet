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

@Database(
    entities = [
        UserEntity::class,
        AppointmentEntity::class
               ],
    version = 3,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun appointmentDao(): AppointmentDao

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