package com.mobileapp.rpm.githubusers.data.remote

import com.mobileapp.rpm.githubusers.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface RemoteService {

    @GET(RemoteContract.API_GET_USER)
    fun netCallGetUser(): Observable<List<User>>

}