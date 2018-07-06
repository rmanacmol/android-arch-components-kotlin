package com.mobileapp.rpm.githubusers.data.local.table

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.mobileapp.rpm.githubusers.data.local.RoomContract
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntity: List<UserEntity>)

    @Query(RoomContract.select_from_user)
    fun selectAll(): Flowable<List<UserEntity>>

}
