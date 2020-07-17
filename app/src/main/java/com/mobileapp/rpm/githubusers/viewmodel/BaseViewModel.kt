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

package com.mobileapp.rpm.githubusers.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
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
