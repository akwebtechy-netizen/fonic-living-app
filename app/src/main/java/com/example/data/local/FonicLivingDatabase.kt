package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InquiryEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FonicLivingDatabase : RoomDatabase() {
    abstract fun dao(): FonicLivingDao

    companion object {
        @Volatile
        private var INSTANCE: FonicLivingDatabase? = null

        fun getInstance(context: Context): FonicLivingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FonicLivingDatabase::class.java,
                    "fonic_living.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
