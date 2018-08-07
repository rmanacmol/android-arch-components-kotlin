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
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var viewModel: DetailViewModel? = null
    var builder: CustomTabsIntent.Builder? = null
    var customTabsIntent: CustomTabsIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        builder = CustomTabsIntent.Builder()
        builder?.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        customTabsIntent = builder?.build()

        populateUserDetail()

        close.setOnClickListener {
            finish()
        }

    }

    fun populateUserDetail() {
        progress(View.VISIBLE, View.GONE)
        viewModel?.getUserDetail(this.intent.getStringExtra("username"))?.observe(this,
                Observer { userDetail ->

                    if (userDetail != null) {
                        if (userDetail.site_admin) {
                            siteadmin.visibility = View.VISIBLE
                        } else {
                            siteadmin.visibility = View.GONE
                        }
                        tvName.text = userDetail.name
                        login.text = userDetail.login
                        location.text = userDetail.location
                        blog.text = userDetail.blog
                        tvBio.text = userDetail.bio

                        Glide.with(this).load(userDetail.avatar_url).preload();
                        Glide.with(this).load(userDetail.avatar_url).into(profile_image)

                        blog.setOnClickListener {
                            customTabsIntent?.launchUrl(this, Uri.parse(userDetail.blog))
                        }
                        progress(View.GONE, View.VISIBLE)

                    } else {
                        Toast.makeText(this, "Failed Fetching Remote Data", Toast.LENGTH_LONG).show()
                        progress(View.GONE, View.GONE)
                        finish()
                    }
                })

    }

    fun progress(progressView: Int, detailView: Int) {
        progress.visibility = progressView
        lnDetail.visibility = detailView

    }

}
