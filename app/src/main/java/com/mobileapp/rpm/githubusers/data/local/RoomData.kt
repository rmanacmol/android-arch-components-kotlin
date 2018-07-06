package com.mobileapp.rpm.githubusers.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
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
