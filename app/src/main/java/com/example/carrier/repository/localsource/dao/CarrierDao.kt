package com.example.carrier.repository.localsource.dao

import androidx.room.*
import com.example.carrier.repository.localsource.entity.ShiftDetailsEntity

@Dao
interface CarrierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShiftDetails(shiftDetailsEntity: ShiftDetailsEntity)

    @Query("SELECT * FROM ShiftDetails")
    fun getAllDetails(): ShiftDetailsEntity

    @Query("SELECT * FROM ShiftDetails WHERE id = :id")
    fun getShiftWithSameId(id: Int): ShiftDetailsEntity?

    @Query("DELETE FROM ShiftDetails")
    fun deleteAllDetails()

    @Transaction
    suspend fun deleteAndInsertDetails(shiftDetailsEntity: ShiftDetailsEntity) {
        deleteAllDetails()
        insertShiftDetails(shiftDetailsEntity)
    }
}