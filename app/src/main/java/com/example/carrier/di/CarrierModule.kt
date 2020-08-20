package com.example.carrier.di

import android.app.Application
import com.example.carrier.repository.ShiftRepository
import com.example.carrier.repository.ShiftRepositoryImpl
import com.example.carrier.repository.localsource.ShiftLocalDataSource
import com.example.carrier.repository.localsource.ShiftLocalDataSourceImpl
import com.example.carrier.repository.localsource.database.CarrierDatabase
import com.example.carrier.repository.remotesource.ShiftRemoteDataSource
import com.example.carrier.repository.remotesource.ShiftRemoteDataSourceImpl
import com.example.carrier.repository.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CarrierModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): ShiftRemoteDataSource =
        ShiftRemoteDataSourceImpl.create(Constants.BASE_URL)

    @Provides
    @Singleton
    fun provideLocalDataSource(application: Application): ShiftLocalDataSource =
        ShiftLocalDataSourceImpl(CarrierDatabase.getInstance(application).carrierDao())

    @Provides
    @Singleton
    fun provideRepository(shiftRemoteDataSource: ShiftRemoteDataSource, shiftLocalDataSource: ShiftLocalDataSource): ShiftRepository =
        ShiftRepositoryImpl(shiftRemoteDataSource, shiftLocalDataSource)

}