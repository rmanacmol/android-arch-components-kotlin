package com.mobileapp.rpm.githubusers.viewmodel

import android.arch.lifecycle.LiveData
import com.mobileapp.rpm.githubusers.model.User
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var liveData: LiveData<List<User>>? = null

    fun getUser(): LiveData<List<User>>? {

        return repository.getUser()

    }

}