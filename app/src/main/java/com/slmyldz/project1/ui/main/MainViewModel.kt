package com.slmyldz.project1.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.slmyldz.project1.network.Api
import com.slmyldz.project1.network.Page
import com.slmyldz.project1.network.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private var api: Api) : ViewModel() {

    val currentList: MutableLiveData<MutableList<User>> by lazy {
        MutableLiveData<MutableList<User>>()
    }

    var pageCount = 0

    val list = mutableListOf<User>()

    fun getUsers() {
        pageCount++
        api.getUsers(pageCount).enqueue(object : Callback<Page> {

            override fun onFailure(call: Call<Page>, t: Throwable) {

            }

            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    list.addAll(user!!)
                    currentList.postValue(list)
                }
            }
        })
    }

}
