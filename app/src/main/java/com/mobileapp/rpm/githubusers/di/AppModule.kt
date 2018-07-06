package com.mobileapp.rpm.githubusers.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val githubUserApplication: GithubUserApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = githubUserApplication

}