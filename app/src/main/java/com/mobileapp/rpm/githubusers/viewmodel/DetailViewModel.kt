package com.mobileapp.rpm.githubusers.viewmodel

import android.arch.lifecycle.LiveData
import com.mobileapp.rpm.githubusers.model.UserDetail

class DetailViewModel : BaseViewModel() {

    fun getUserDetail(login: String): LiveData<UserDetail>? {
        return repository.getUserDetail(login)
    }

}