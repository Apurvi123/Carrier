package com.example.carrier

import android.app.Activity
import android.app.Application
import com.example.carrier.di.DaggerCarrierComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CarrierApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerCarrierComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}