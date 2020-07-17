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

package com.mobileapp.rpm.githubusers.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.mobileapp.rpm.githubusers.data.local.table.UserDao
import com.mobileapp.rpm.githubusers.data.local.table.UserEntity

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class RoomData : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        fun buildPersistentCurrency(context: Context):
                RoomData = Room.databaseBuilder(context.applicationContext,
                RoomData::class.java, RoomContract.appdb).build()

    }

}
