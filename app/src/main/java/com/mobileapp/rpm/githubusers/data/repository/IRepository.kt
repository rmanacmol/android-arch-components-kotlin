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

import androidx.lifecycle.MutableLiveData
import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.model.UserDetail

interface IRepository {

    fun getUser(): MutableLiveData<List<User>>

    fun getUserDetail(login: String): MutableLiveData<UserDetail>

    fun addUserToLocal(listUser : List<User>)

    fun getUserLocal(): MutableLiveData<List<User>>

}