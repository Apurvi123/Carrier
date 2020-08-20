package com.example.carrier.repository.localsource.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Shift details entity
 */
@Entity(tableName = "ShiftDetails")
data class ShiftDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "status") val shiftStatus: Int
)