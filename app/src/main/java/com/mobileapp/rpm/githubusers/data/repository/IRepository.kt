package com.mobileapp.rpm.githubusers.data.repository

import android.arch.lifecycle.MutableLiveData
import com.mobileapp.rpm.githubusers.model.User

interface IRepository {

    fun getUser(): MutableLiveData<List<User>>

}