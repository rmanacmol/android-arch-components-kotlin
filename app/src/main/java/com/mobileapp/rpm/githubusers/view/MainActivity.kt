package com.mobileapp.rpm.githubusers.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.view.adapter.UserAdapter
import com.mobileapp.rpm.githubusers.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        populateUser()

        swipeRefreshLayout.setOnRefreshListener { populateUser() }

    }

    fun populateUser() {
        swipeRefreshLayout.isRefreshing = true
        viewModel?.getUser()?.observe(this,
                Observer { userList ->
                    if (userList != null) {
                        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                        rv.adapter = UserAdapter(userList as ArrayList<User>)

                    } else {
                        Toast.makeText(this, "Failed Fetching Data", Toast.LENGTH_LONG).show()
                    }
                    swipeRefreshLayout.isRefreshing = false
                })
    }

}
