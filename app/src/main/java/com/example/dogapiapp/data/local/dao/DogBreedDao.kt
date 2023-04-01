package com.example.dogapiapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogapiapp.data.local.model.DogBreedDbModel
import com.example.dogapiapp.data.local.model.DogBreedRemoteKeys
import io.reactivex.rxjava3.core.Single

@Dao
interface DogBreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(dogBreeds: List<DogBreedDbModel>)

    @Query("select * from dog_breeds order by sort_order")
    fun getDogBreedsFromDb(): PagingSource<Int, DogBreedDbModel>

    @Query("select * from dog_breeds")
    fun getDogBreedsFromDbWithoutPaging(): List<DogBreedDbModel>

    @Query("select * from dog_breeds where id = :id")
    fun getDogBreedById(id: Int): Single<DogBreedDbModel>

    @Query("delete from dog_breeds")
    fun deleteAllDogBreeds()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRemoteKeys(remoteKeys: List<DogBreedRemoteKeys>)

    @Query("select * from dog_breeds_remote_keys where id = :id")
    fun getDogBreedRemoteKeyById(id: Int): DogBreedRemoteKeys?

    @Query("delete from dog_breeds_remote_keys")
    fun deleteAllRemoteKeys()
}