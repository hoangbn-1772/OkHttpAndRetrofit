package com.sun.okhttp_retrofit.di

import com.sun.okhttp_retrofit.ui.retrofit.login.LoginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { LoginViewModel(get()) }
}
