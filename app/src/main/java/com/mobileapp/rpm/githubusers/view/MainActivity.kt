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

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        populateUser()
        swipeRefreshLayout.setOnRefreshListener { populateUser() }
    }

    private fun populateUser() {
        swipeRefreshLayout.isRefreshing = true
        viewModel?.getUser()?.observe(this,
                Observer { userList ->
                    if (userList != null) {
                        viewModel?.addUserToLocal(userList)
                        rv.adapter = UserAdapter(userList as ArrayList<User>)
                    } else {
                        callDataFromLocal()
                        Toast.makeText(this, "Failed Fetching Remote Data", Toast.LENGTH_LONG).show()
                    }
                    swipeRefreshLayout.isRefreshing = false
                })

    }

    private fun callDataFromLocal() {
        viewModel?.getUserFromLocal()?.observe(this, Observer { userList ->
            rv.adapter = UserAdapter(userList as ArrayList<User>)
        })

    }
}
