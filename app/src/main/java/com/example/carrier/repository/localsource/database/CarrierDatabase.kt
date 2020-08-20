package com.example.carrier.repository.localsource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carrier.repository.localsource.dao.CarrierDao
import com.example.carrier.repository.localsource.entity.ShiftDetailsEntity
import com.example.carrier.repository.util.Constants

/**
 * Database holder consisting the entities and db version
 */
@Database(
    entities = [ShiftDetailsEntity::class],
    version = Constants.DB_VERSION,
    exportSchema = false
)
internal abstract class CarrierDatabase : RoomDatabase() {

    abstract fun carrierDao(): CarrierDao

    companion object {
        // Using Room's Database builder
        fun getInstance(context: Context): CarrierDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CarrierDatabase::class.java,
                Constants.DB_NAME
            ).build()
        }
    }
}