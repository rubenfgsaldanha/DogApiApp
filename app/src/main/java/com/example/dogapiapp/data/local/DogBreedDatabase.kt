package com.example.dogapiapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dogapiapp.data.local.converters.DogBreedConverters
import com.example.dogapiapp.data.local.dao.DogBreedDao
import com.example.dogapiapp.data.local.model.*

@Database(
    entities = [
        DogBreedDbModel::class,
        HeightDbModel::class,
        WeightDbModel::class,
        ImageDbModel::class,
        DogBreedRemoteKeys::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DogBreedConverters::class)
abstract class DogBreedDatabase : RoomDatabase() {

    abstract fun dogBreedDao(): DogBreedDao

    companion object {

        @Volatile
        private var instance: DogBreedDatabase? = null

        fun getInstance(context: Context): DogBreedDatabase {

            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context, useInMemory: Boolean = false): DogBreedDatabase {
            val databaseBuilder = if(useInMemory){
                Room.inMemoryDatabaseBuilder(context, DogBreedDatabase::class.java)
            }
            else{
                Room.databaseBuilder(context.applicationContext, DogBreedDatabase::class.java, "DogBreedDatabase.db")
            }
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }
}