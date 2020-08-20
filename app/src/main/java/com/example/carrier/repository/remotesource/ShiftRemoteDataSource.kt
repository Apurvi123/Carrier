package com.example.carrier.repository.remotesource

import com.example.carrier.repository.remotesource.model.MessageSuccess
import com.example.carrier.repository.remotesource.model.ShiftDetails
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * ShiftRemoteDataSource is an abstraction of remote api
 */
interface ShiftRemoteDataSource {

    /**
     * MEthod to get all the recent shift details info
     */
    suspend fun getShiftDetails(): Deferred<Response<ShiftDetails>>

    /**
     * Method to post message
     */
    suspend fun postMessage(): Deferred<Response<MessageSuccess>>
}