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

package com.mobileapp.rpm.githubusers.view.adapter

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.databinding.ItemRowHeaderBinding
import com.mobileapp.rpm.githubusers.databinding.ItemRowUserBinding
import com.mobileapp.rpm.githubusers.model.User
import com.mobileapp.rpm.githubusers.view.DetailActivity
import java.util.*

class UserAdapter(userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UViewHolder>() {

    private val users: MutableList<User>

    init {
        this.users = ArrayList()
        this.users.clear()
        this.users.add(0, User("Header"))
        this.users.addAll(userList)
    }

    companion object {
        private const val HEADER_1 = 0
        private const val USER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding? = null
        when (viewType) {
            HEADER_1 -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_row_header, parent, false)
                return UViewHolder(binding as ItemRowHeaderBinding)
            }
            USER -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_row_user, parent, false)
                return UViewHolder(binding as ItemRowUserBinding)
            }
        }
        return UViewHolder(binding as ItemRowHeaderBinding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UViewHolder, position: Int) {

        when (holder.itemViewType) {
            HEADER_1 -> holder.itemRowHeaderBinding?.tvHeader?.text = holder.itemView.context.getString(R.string.string_user)

            USER -> {
                holder.bindItem(users[position])
                val user = users[position]

                holder.itemRowUserBinding?.login?.text = user.login

                Glide.with(holder.itemView.context).load(user.avatar_url).apply {
                    preload()
                    into(holder.itemRowUserBinding?.avatar!!)
                }

                if (user.site_admin) {
                    holder.itemRowUserBinding?.siteadmin?.visibility = View.VISIBLE
                } else {
                    holder.itemRowUserBinding?.siteadmin?.visibility = View.GONE
                }

                holder.itemView.setOnClickListener {
                    val i = Intent(holder.itemView.context, DetailActivity::class.java)
                    i.putExtra("username", user.login)
                    holder.itemView.context.startActivity(i)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class UViewHolder : RecyclerView.ViewHolder {

        var itemRowUserBinding: ItemRowUserBinding? = null
        var itemRowHeaderBinding: ItemRowHeaderBinding? = null

        internal constructor(binding: ItemRowHeaderBinding) : super(binding.root) {
            this.itemRowHeaderBinding = binding
        }

        internal constructor(binding: ItemRowUserBinding) : super(binding.root) {
            this.itemRowUserBinding = binding
        }

        internal fun bindItem(user: User) {
            itemRowUserBinding?.model = user
            itemRowUserBinding?.executePendingBindings()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_1
        } else {
            USER
        }

    }
}
