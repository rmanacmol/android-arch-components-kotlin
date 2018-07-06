package com.mobileapp.rpm.githubusers.data.remote

import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.model.UserDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService {

    @GET(RemoteContract.API_GET_USER)
    fun netCallGetUser(): Observable<List<User>>

    @GET(RemoteContract.API_GET_USER_DETAIL)
    fun netCallUserDetail(@Path("username") login: String): Observable<UserDetail>

}