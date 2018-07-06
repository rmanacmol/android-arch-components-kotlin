package com.mobileapp.rpm.githubusers.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel?.getUser()?.observe(this,
                Observer { userlist ->

                    Log.d("userlist", userlist.toString())

                })

    }
}
