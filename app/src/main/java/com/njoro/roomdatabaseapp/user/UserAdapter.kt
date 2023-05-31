package com.njoro.roomdatabaseapp.user

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.njoro.roomdatabaseapp.R
import com.njoro.roomdatabaseapp.database.UserEntity

class UserAdapter: ListAdapter<UserEntity,UserAdapter.UserViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = getItem(position)
        Log.e("USER ID", currentUser.userId.toString())
        holder.bind(currentUser)
    }

    class UserViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView){
        private val userItemView: TextView = itemView.findViewById(R.id.txv_name)
        private val userId: TextView = itemView.findViewById(R.id.txv_id)
        private val email: TextView = itemView.findViewById(R.id.txv_email)

        fun bind(currentUser: UserEntity) {
            userId.text = "Id ${currentUser.userId}"
            userItemView.text ="Username ${currentUser.username}"
            email.text ="Emails ${currentUser.email}"
        }

        companion object{
            fun create(parent: ViewGroup): UserViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item,parent,false)
                return UserViewHolder(view)
            }
        }
    }

    class UserComparator: DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.userId == oldItem.userId
        }


    }
}