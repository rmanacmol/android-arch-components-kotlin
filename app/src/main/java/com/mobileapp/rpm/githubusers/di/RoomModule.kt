package com.mobileapp.rpm.githubusers.di

import android.content.Context
import com.mobileapp.rpm.githubusers.data.local.RoomData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomCurrencyDataSource(context: Context) = RoomData.buildPersistentCurrency(context)

}