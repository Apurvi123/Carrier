package com.example.carrier.di

import com.example.carrier.ui.activity.ShiftDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CarrierActivityModule {

    @ContributesAndroidInjector()
    abstract fun shiftDetailsActivity(): ShiftDetailsActivity
}