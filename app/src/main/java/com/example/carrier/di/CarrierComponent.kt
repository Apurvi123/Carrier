package com.example.carrier.di

import android.app.Application
import com.example.carrier.CarrierApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [CarrierModule::class,
    CarrierActivityModule::class,
    AndroidSupportInjectionModule::class])
@Singleton
interface CarrierComponent {

    /*
     * We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     *
     * */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CarrierComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(carrierApplication: CarrierApplication)
}