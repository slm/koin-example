package com.slmyldz.project1.di

import com.slmyldz.project1.MainActivity
import com.slmyldz.project1.network.Api
import com.slmyldz.project1.ui.main.MainFragment
import com.slmyldz.project1.ui.main.MainViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    fun buildApi(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(Api::class.java)
        return api
    }
    single<Api>(createdAtStart = true) { buildApi() }
}

val mvvmModule = module {

    viewModel {  MainViewModel(get()) }

    scope<MainActivity> {
        fragment { MainFragment() }
        viewModel { MainViewModel(get()) }

    }
}