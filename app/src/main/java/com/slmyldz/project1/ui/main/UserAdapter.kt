package com.slmyldz.project1.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slmyldz.project1.R
import com.slmyldz.project1.network.User

class UserAdapter(val onItemClick: OnItemClick) : RecyclerView.Adapter<UserHolder>() {

    var list: MutableList<User> = mutableListOf()
    var query:String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    fun query(it: String) {
        //TODO implement this
    }

    fun clearQuery() {
        //TODO implement this
    }

}

interface OnItemClick {
    fun onOnItemClick(user: User)
}

class UserHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val profile: ImageView = view.findViewById(R.id.profile)
    val name: TextView = view.findViewById(R.id.name)

    fun bind(user: User, onItemClick: OnItemClick) {
        view.setOnClickListener {
            onItemClick.onOnItemClick(user)
        }
        Glide.with(profile).load(user.avatar).into(profile)
        name.setText("${user.first_name} ${user.last_name}")
    }

}