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
                        viewModel?.addUserToLocal(userList)

                        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                        rv.adapter = UserAdapter(userList as ArrayList<User>)

                    } else {
                        callDataFromLocal()
                        Toast.makeText(this, "Failed Fetching Remote Data", Toast.LENGTH_LONG).show()
                    }
                    swipeRefreshLayout.isRefreshing = false
                })

    }

    fun callDataFromLocal() {
        viewModel?.getUserFromLocal()?.observe(this, Observer { userList ->
            rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            rv.adapter = UserAdapter(userList as ArrayList<User>)
        })

    }
}
