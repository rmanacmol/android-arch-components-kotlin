package com.mobileapp.rpm.githubusers.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import com.mobileapp.rpm.githubusers.data.repository.Repository
import com.mobileapp.rpm.githubusers.di.GithubUserApplication
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var repository: Repository

    init {
        GithubUserApplication.appComponent.inject(this)
    }

    override fun onCleared() {
        unSubscribeViewModel()
        super.onCleared()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in repository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }

}
