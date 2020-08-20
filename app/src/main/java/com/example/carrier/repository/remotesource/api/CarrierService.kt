package com.example.carrier.repository.remotesource.api

import com.example.carrier.repository.remotesource.model.MessageSuccess
import com.example.carrier.repository.remotesource.model.ShiftDetails
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * APIs for Carrier app
 */
interface CarrierService {

    // For this mock, not using shiftId in the path of URL
    // For actual requests, we should take @path method param that takes in the shift id value - {id}
    @GET("v3/ebedab87-4944-4c0e-b553-dfb8452ae727")
    fun getShiftDetails(): Deferred<Response<ShiftDetails>>

    // This should actually be a POST request that takes in @Body param with MessageBody data class
    // For the sake of mock, created a GET request that returns a MessageSuccess with result
    @GET("v3/be3c0a92-10c1-4fdc-8f4e-93b5dd74e201")
    fun postMessage(): Deferred<Response<MessageSuccess>>
}