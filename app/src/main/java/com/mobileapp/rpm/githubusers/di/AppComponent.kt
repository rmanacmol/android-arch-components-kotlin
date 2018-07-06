package com.mobileapp.rpm.githubusers.di

import com.mobileapp.rpm.githubusers.viewmodel.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RoomModule::class, NetModule::class))
@Singleton
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)

}