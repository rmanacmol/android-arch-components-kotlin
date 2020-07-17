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

package com.mobileapp.rpm.githubusers.data.local.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobileapp.rpm.githubusers.data.local.RoomContract

@Entity(tableName = RoomContract.table_user)
data class UserEntity(
        @PrimaryKey(autoGenerate = true) val iid: Int,
        var login: String,
        var id: Int,
        var node_id: String,
        var avatar_url: String,
        var gravatar_id: String,
        var url: String,
        var html_url: String,
        var followers_url: String,
        var following_url: String,
        var gists_url: String,
        var starred_url: String,
        var subscriptions_url: String,
        var organizations_url: String,
        var repos_url: String,
        var events_url: String,
        var received_events_url: String,
        var type: String,
        var site_admin: Boolean)