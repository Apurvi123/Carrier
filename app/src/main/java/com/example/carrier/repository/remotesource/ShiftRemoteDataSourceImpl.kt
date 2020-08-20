package com.example.carrier.repository.remotesource

import com.example.carrier.repository.remotesource.api.CarrierService
import com.example.carrier.repository.remotesource.model.MessageSuccess
import com.example.carrier.repository.remotesource.model.ShiftDetails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShiftRemoteDataSourceImpl(private val carrierService: CarrierService): ShiftRemoteDataSource {

    companion object {

        fun create(baseURL: String): ShiftRemoteDataSource {
            // Initialization of Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            val carrierService = retrofit.create(CarrierService::class.java)
            return ShiftRemoteDataSourceImpl(carrierService)
        }
    }
    override suspend fun getShiftDetails(): Deferred<Response<ShiftDetails>> = carrierService.getShiftDetails()

    override suspend fun postMessage(): Deferred<Response<MessageSuccess>> = carrierService.postMessage()
}