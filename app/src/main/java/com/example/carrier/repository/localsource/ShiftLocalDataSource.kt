package com.example.carrier.repository.localsource

import com.example.carrier.repository.localsource.entity.ShiftDetailsEntity

/**
 * API for local data source that communicates to Room DB
 */
interface ShiftLocalDataSource {

    /**
     * Method to insert trucker shift details into ShiftDetailsEntity
     */
    suspend fun insertShiftDetails(shiftDetailsEntity: ShiftDetailsEntity)

    /**
     * Method to get the shift details from database
     */
    fun getShiftDetails(): ShiftDetailsEntity

    /**
     * Method to get single record that matches the id
     */
    fun getShiftWithSameId(id: Int): ShiftDetailsEntity?

    /**
     * Method to clear entity from db
     */
    suspend fun clearEntity()
}