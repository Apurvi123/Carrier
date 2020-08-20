package com.example.carrier.repository.remotesource.model

import com.google.gson.annotations.SerializedName

/**
 * Data Model for shift details of truckers
 */
data class ShiftDetails (
    val id: Int,
    val status: String,
    @SerializedName("driver_id")
    val driverId: Int,
    @SerializedName("driver_name")
    val driverName: String
)