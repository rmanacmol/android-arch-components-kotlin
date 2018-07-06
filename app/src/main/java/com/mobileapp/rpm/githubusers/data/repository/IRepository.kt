package com.mobileapp.rpm.githubusers.data.repository

import android.arch.lifecycle.MutableLiveData
import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.model.UserDetail

interface IRepository {

    fun getUser(): MutableLiveData<List<User>>

    fun getUserDetail(login: String): MutableLiveData<UserDetail>

    fun addUserToLocal(listUser : List<User>)

    fun getUserLocal(): MutableLiveData<List<User>>

}