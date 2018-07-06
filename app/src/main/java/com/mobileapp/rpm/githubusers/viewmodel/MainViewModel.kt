package com.mobileapp.rpm.githubusers.viewmodel

import android.arch.lifecycle.LiveData
import com.mobileapp.rpm.githubusers.model.User

class MainViewModel : BaseViewModel() {

    fun getUser(): LiveData<List<User>>? {
        return repository.getUser()
    }

}