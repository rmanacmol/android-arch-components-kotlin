package com.mobileapp.rpm.githubusers.data.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.mobileapp.rpm.githubusers.data.local.RoomData
import com.mobileapp.rpm.githubusers.data.remote.RemoteData
import com.mobileapp.rpm.githubusers.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
                     Log.v("error", error.message)
                    mutableLiveData.value = null
                })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

}