package com.example.carrier.repository.remotesource.model

/**
 * Enum for shift status
 */
enum class ShiftStatus(val shiftStatus: String, val status: Int) {
    SCHEDULED("scheduled", 0),
    STARTED("started", 1),
    FINISHED("finished", 2);

    companion object {
        fun toInt(statusFromAPI: String): Int {
            values().forEach { if (it.shiftStatus.equals(statusFromAPI, true)) return it.status }
            return 0
        }

        fun fromInt(status: Int?): String {
            values().forEach { if (it.status == status) return it.shiftStatus }
            return ""
        }
    }
}