package com.mobileapp.rpm.githubusers.viewmodel

import android.arch.lifecycle.ViewModel
import com.mobileapp.rpm.githubusers.data.repository.Repository
import com.mobileapp.rpm.githubusers.di.GithubUserApplication
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject lateinit var repository: Repository

    init {
        GithubUserApplication.appComponent.inject(this)
    }

}
