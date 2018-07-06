package com.mobileapp.rpm.githubusers.di

import android.app.Application
import timber.log.BuildConfig
import timber.log.Timber

class GithubUserApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
                .netModule(NetModule(this)).build()
    }
}
