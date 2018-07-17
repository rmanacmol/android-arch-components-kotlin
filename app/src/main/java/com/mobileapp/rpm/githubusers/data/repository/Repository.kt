/**
 * Copyright 2018 Renz Manacmol.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobileapp.rpm.githubusers.data.repository

import android.arch.lifecycle.MutableLiveData
import com.mobileapp.rpm.githubusers.data.local.RoomData
import com.mobileapp.rpm.githubusers.data.local.table.UserEntity
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

    //LOCAL
    override fun addUserToLocal(listUser: List<User>) {
        val userList = ArrayList<UserEntity>()
        listUser.forEach {
            userList.add(UserEntity(it.id,
                    it.login,
                    it.id,
                    it.node_id,
                    it.avatar_url,
                    it.gravatar_id,
                    it.url,
                    it.html_url,
                    it.followers_url,
                    it.following_url,
                    it.gists_url,
                    it.starred_url,
                    it.subscriptions_url,
                    it.organizations_url,
                    it.repos_url,
                    it.events_url,
                    it.received_events_url,
                    it.type,
                    it.site_admin
            ))
        }
        roomData.userDao().insertAll(userList)
    }

    override fun getUserLocal(): MutableLiveData<List<User>> {

        val mutableLiveData = MutableLiveData<List<User>>()

        val disposable = roomData.userDao().selectAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    mutableLiveData.value = transform(userList)
                }, { t: Throwable? -> t?.printStackTrace() })
        allCompositeDisposable.add(disposable)


        return mutableLiveData
    }

    private fun transform(users: List<UserEntity>): ArrayList<User> {
        val userList = ArrayList<User>()
        users.forEach {
            userList.add(
                    User(it.login,
                            it.id,
                            it.node_id,
                            it.avatar_url,
                            it.gravatar_id,
                            it.url,
                            it.html_url,
                            it.followers_url,
                            it.following_url,
                            it.gists_url,
                            it.starred_url,
                            it.subscriptions_url,
                            it.organizations_url,
                            it.repos_url,
                            it.events_url,
                            it.received_events_url,
                            it.type,
                            it.site_admin
                    ))
        }
        return userList
    }
}