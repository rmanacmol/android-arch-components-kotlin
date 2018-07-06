package com.mobileapp.rpm.githubusers.data.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.mobileapp.rpm.githubusers.data.local.RoomData
import com.mobileapp.rpm.githubusers.data.remote.RemoteData
import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.model.UserDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject constructor(
        private val roomData: RoomData,
        private val remoteData: RemoteData
) : IRepository {

    //REMOTE CALL
    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getUser(): MutableLiveData<List<User>> {
        val mutableLiveData = MutableLiveData<List<User>>()
        val disposable = remoteData.netCallGetUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userlist ->
                    mutableLiveData.value = userlist
                }, { error ->
                    Timber.i("error: " + error.message)
                    mutableLiveData.value = null
                })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun getUserDetail(login: String): MutableLiveData<UserDetail> {
        val mutableLiveData = MutableLiveData<UserDetail>()
        val disposable = remoteData.netCallGetUserDetail(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userDetail ->
                    mutableLiveData.value = userDetail
                }, { error ->
                    Timber.i("error: " + error.message)
                    mutableLiveData.value = null
                })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }
}