package com.slmyldz.project1.network

import java.io.Serializable

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
):Serializable

data class Page(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_page: Int,
    var data: MutableList<User>
)