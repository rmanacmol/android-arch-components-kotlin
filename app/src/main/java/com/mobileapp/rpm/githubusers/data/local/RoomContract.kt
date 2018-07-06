package com.mobileapp.rpm.githubusers.data.local

class RoomContract {

    companion object {

        private const val select_from = "SELECT * FROM "

        const val appdb = "user.db"
        const val table_user = "user"
        const val select_from_user = select_from + table_user

    }

}