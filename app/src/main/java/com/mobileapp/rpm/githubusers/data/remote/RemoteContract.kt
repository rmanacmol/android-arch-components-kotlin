package com.mobileapp.rpm.githubusers.data.remote

class RemoteContract {

    companion object {

        const val API_GET_USER = "users?page=1&per_page=100"

        const val API_GET_USER_DETAIL = "users/{username}"

    }

}