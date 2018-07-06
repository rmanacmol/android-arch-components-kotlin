package com.mobileapp.rpm.githubusers.data.remote

import javax.inject.Inject

class RemoteData @Inject constructor(private val remoteService: RemoteService) {

    fun netCallGetUser() = remoteService.netCallGetUser()

    fun netCallGetUserDetail(login: String) = remoteService.netCallUserDetail(login)

}