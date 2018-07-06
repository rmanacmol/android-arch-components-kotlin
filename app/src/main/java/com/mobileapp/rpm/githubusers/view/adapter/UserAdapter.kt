package com.mobileapp.rpm.githubusers.view.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.databinding.ItemRowHeaderBinding
import com.mobileapp.rpm.githubusers.databinding.ItemRowUserBinding
import com.mobileapp.rpm.githubusers.model.User


class UserAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UViewHolder>() {

    init {
        userList.add(0,User("header"))
    }

    companion object {
        private val HEADER_1 = 0
        private val USER = 1
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
                holder.bindItem(userList[position])
                val user = userList[position]

                holder.itemRowUserBinding?.login?.text = user.login

                Glide.with(holder.itemView.context).load(user.avatar_url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(holder.itemRowUserBinding?.avatar)

                if(user.site_admin) {
                    holder.itemRowUserBinding?.siteadmin?.visibility = View.VISIBLE
                } else {
                    holder.itemRowUserBinding?.siteadmin?.visibility = View.GONE
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
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
