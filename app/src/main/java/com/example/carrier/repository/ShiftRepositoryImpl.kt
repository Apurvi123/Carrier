package com.example.carrier.repository

import android.util.Log
import com.example.carrier.repository.localsource.ShiftLocalDataSource
import com.example.carrier.repository.localsource.entity.ShiftDetailsEntity
import com.example.carrier.repository.remotesource.ShiftRemoteDataSource
import com.example.carrier.repository.remotesource.model.MessageBody
import com.example.carrier.repository.remotesource.model.MessageSuccess
import com.example.carrier.repository.remotesource.model.ShiftDetails
import com.example.carrier.repository.remotesource.model.ShiftStatus
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.Exception

/**
 * This class is responsible for communicating with both remote and local data sources.
 * It requests the api for new data. Once new data is received, the repo should update the local source,
 * keeping our local persistence as single source of truth.
 */
class ShiftRepositoryImpl(
    private val shiftRemoteDataSource: ShiftRemoteDataSource,
    private val shiftLocalDataSource: ShiftLocalDataSource
) : ShiftRepository {

    init {
        fetchShiftDetailsfromAPI()
    }

    private lateinit var shiftDetails: ShiftDetails

    override fun getShiftDetails(id: Int): ShiftDetails {
        // Get corresponding entity for the requested shift id from db
        val detailsEntity = shiftLocalDataSource.getShiftWithSameId(id)
        // For status alone, if there is a matching shift stored locally, then what is stored locally is returned otherwise
        // the data from api is returned. For all other fields, data from api is returned.
        return if (detailsEntity == null) {
            fetchShiftDetailsfromAPI()
            shiftDetails
        } else {
            ShiftDetails(shiftDetails.id,
                ShiftStatus.fromInt(detailsEntity.shiftStatus),
                shiftDetails.driverId,
                shiftDetails.driverName)
        }
    }

    override fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            shiftLocalDataSource.clearEntity()
        }
    }

    override fun sendMessage(messageBody: MessageBody): MessageSuccess? {
        return runBlocking {
            val messageSuccess = async { shiftRemoteDataSource.postMessage().await() }
            messageSuccess.await().body()
            }
    }

    // Method to fetch from the api and update the local entity
    private fun fetchShiftDetailsfromAPI() {
        runBlocking {
            withContext(Dispatchers.IO) {
                try {
                    val response = shiftRemoteDataSource.getShiftDetails().await()
                    if (!response.isSuccessful) {
                        val errorBody = JSONObject(response.errorBody()?.string().orEmpty())
                        Log.d(TAG, "API request failed with - ${errorBody.getString("message")}")
                    }

                    response.body()?.run {
                        Log.d(TAG, "API network request is success = ${response.code()}")
                        shiftDetails = this
                        shiftLocalDataSource.insertShiftDetails(
                            ShiftDetailsEntity(
                                this.id,
                                ShiftStatus.toInt(this.status)
                            )
                        )
                    }
                } catch (exception: Exception) {
                    // Possible exceptions like certification path not found, CertPathValidatorException
                    Log.d(TAG, "Couldn't process the API request - ${exception.message}")
                }
            }
        }
    }
}

private const val TAG = "ShiftRepositoryImpl"