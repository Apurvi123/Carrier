package com.example.carrier.repository

import com.example.carrier.repository.remotesource.model.MessageBody
import com.example.carrier.repository.remotesource.model.MessageSuccess
import com.example.carrier.repository.remotesource.model.ShiftDetails
import java.lang.Exception

/**
 * API to communicate to both the data sources and give the most recent data
 */
interface ShiftRepository {

    /**
     * Method to get all details from the local entity
     */
    fun getShiftDetails(id: Int): ShiftDetails

    /**
     * Method to clear all entities
     */
    fun clearAll()

    /**
     * Method to send message
     */
    fun sendMessage(messageBody: MessageBody): MessageSuccess?
}