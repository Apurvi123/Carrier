package com.example.carrier.repository.localsource

import com.example.carrier.repository.localsource.dao.CarrierDao
import com.example.carrier.repository.localsource.entity.ShiftDetailsEntity

class ShiftLocalDataSourceImpl(private val carrierDao: CarrierDao): ShiftLocalDataSource {

    override suspend fun insertShiftDetails(shiftDetailsEntity: ShiftDetailsEntity) {
        carrierDao.deleteAndInsertDetails(shiftDetailsEntity)
    }

    override fun getShiftDetails() =
        carrierDao.getAllDetails()

    override fun getShiftWithSameId(id: Int) =
        carrierDao.getShiftWithSameId(id)

    override suspend fun clearEntity() {
        carrierDao.deleteAllDetails()
    }
}